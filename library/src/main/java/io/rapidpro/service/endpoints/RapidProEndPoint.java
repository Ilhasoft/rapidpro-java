package io.rapidpro.service.endpoints;

import java.util.Map;

import io.rapidpro.models.ApiResponse;
import io.rapidpro.models.Boundary;
import io.rapidpro.models.Contact;
import io.rapidpro.models.FlowDefinition;
import io.rapidpro.models.FlowRun;
import io.rapidpro.models.FlowStepSet;
import io.rapidpro.models.Group;
import io.rapidpro.models.Message;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gualberto on 6/13/16.
 */
public interface RapidProEndPoint {

    @FormUrlEncoded
    @POST("handlers/gcm/{channel}/")
    Call<ResponseBody> sendReceivedMessage(@Header("Authorization") String token
            , @Path("channel") String channel
            , @Field("from") String from
            , @Field("msg") String msg);

    @GET("api/v1/groups.json")
    Call<Group> listGroups(@Header("Authorization") String token);

    @GET("api/v1/fields.json")
    Call<io.rapidpro.models.Field> listFields(@Header("Authorization") String token);

    @GET("api/v1/boundaries.json")
    Call<Boundary> listBoundaries(@Header("Authorization") String token
            , @Query("page") Integer page, @Query("aliases") Boolean aliases);

    @GET("api/v2/messages.json")
    Call<ApiResponse<Message>> listMessages(@Header("Authorization") String token, @Query("contact") String contactUuid);

    @GET("api/v2/messages.json")
    Call<ApiResponse<Message>> listMessageById(@Header("Authorization") String token, @Query("id") Integer messageId);

    @GET("api/v1/runs.json")
    Call<ApiResponse<FlowRun>>  listRuns(@Header("Authorization") String token
            , @Query("contact") String uuid, @Query("after") String after);

    @GET("api/v1/flow_definition.json")
    Call<FlowDefinition> loadFlowDefinition(@Header("Authorization") String token, @Query("uuid") String flowUuid); //TODO refactor using CALL

    @POST("api/v1/steps")
    @Headers({ "Accept: application/json", "Content-Type: application/json" })
    Map<String, Object> saveFlowStepSet(@Header("Authorization") String token, @Body FlowStepSet flowStepSet);

    @GET("api/v1/contacts.json")
    Call<Contact> loadContact(@Header("Authorization") String token, @Query("urns") String urn);

    @GET("api/v1/contacts.json")
    Call<ApiResponse<Contact>> loadContacts(@Header("Authorization") String token, @Query("urns") String urn);

    @POST("api/v1/contacts.json")
    Call<Contact> saveContact(@Header("Authorization") String token, @Body Contact contact);

}
