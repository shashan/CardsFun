package com.apollosofttech.cards.apihelper;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ANDROID on 12-Jun-18.
 */

public interface ApiHelper {
    @FormUrlEncoded
    @POST("login.php")
    Call<String> login(@Field("aid") String aid, @Field("uname") String uname, @Field("pass") String pass);


    @GET("get_table.php")
    Call<String> get_table(@Query("uname") String user, @Query("boot") int boot);


    @GET("balance.php")
    Call<String> balance(@Query("uname") String user);
   /* @FormUrlEncoded
    @POST("login.php")
    Call<UtilityMessageModel> login(@Field("username") String username,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<UtilityResponseModel> registration(@Field("firstname") String fName,
                                            @Field("lastname") String lName,
                                            @Field("mobile") String number,
                                            @Field("email") String email,
                                            @Field("address") String address,
                                            @Field("dob") String dob,
                                            @Field("gender") String gender,
                                            @Field("password") String password);

    @FormUrlEncoded
    @POST("banar.php")
    Call<UtilityMessageModel> slider(@Field("username") String username);

    @FormUrlEncoded
    @POST("category.php")
    Call<UtilityMessageModel> category(@Field("id") String username);

    @FormUrlEncoded
    @POST("voucherlist.php")
    Call<UtilityResponseModel> voucherList(@Field("username") String username);

    @FormUrlEncoded
    @POST("mywallete.php")
    Call<UtilityResponseModel> walletBalance(@Field("username") String username);

    @FormUrlEncoded
    @POST("product_list.php")
    Call<UtilityMessageModel> getProducts(@Field("categoryid") String categoryId,
                                          @Field("startid") String startId);

    @FormUrlEncoded
    @POST("mywallete_transaction.php")
    Call<UtilityResponseModel> getWalletHistory(@Field("username") String username,
                                                @Field("startid") String startId);

    @FormUrlEncoded
    @POST("addtocart.php")
    Call<UtilityMessageModel> addToCart(@Field("username") String username,
                                        @Field("pid") String pId,
                                        @Field("rate_id") String rateId,
                                        @Field("qty") String qty,
                                        @Field("amount") String amount,
                                        @Field("weight") String weight,
                                        @Field("sortdeacription") String shortDescription,
                                        @Field("proimage") String pImage,
                                        @Field("smartprise") String sPrice);

    @FormUrlEncoded
    @POST("cartlist.php")
    Call<UtilityResponseModel> cartList(@Field("username") String username,
                                        @Field("startid") String startId);

    @FormUrlEncoded
    @POST("delcartproduct.php")
    Call<UtilityMessageModel> removeCartProduct(@Field("username") String username,
                                                @Field("cartid") String cartId);

    @FormUrlEncoded
    @POST("shipaddress.php")
    Call<UtilityResponseModel> fetchShippAddress(@Field("username") String username);

    @FormUrlEncoded
    @POST("shipaddress_update.php")
    Call<UtilityResponseModel> updateShipAddress(@Field("username") String username,
                                                 @Field("shipName") String name,
                                                 @Field("shipPincode") String pincode,
                                                 @Field("shipAddress") String address,
                                                 @Field("shipLandmark") String landmark,
                                                 @Field("shipCity") String city,
                                                 @Field("shipPhoneNumber") String number);

    @FormUrlEncoded
    @POST("updatecart.php")
    Call<UtilityMessageModel> updateCartQty(@Field("username") String username,
                                            @Field("cartid") String cartId,
                                            @Field("qty") String qty);


    @FormUrlEncoded
    @POST("carttotal.php")
    Call<UtilityResponseModel> getTotalCartAmount(@Field("username") String username);

    @FormUrlEncoded
    @POST("check_order_id.php")
    Call<UtilityResponseModel> checkOrderId(@Field("username") String username,
                                            @Field("orderno") String orderId);


    //PAYTM

    @FormUrlEncoded
    @POST("generateChecksum.php")
    Call<Checksum> getChecksum(
            @Field("MID") String mId,
            @Field("ORDER_ID") String orderId,
            @Field("CUST_ID") String custId,
            @Field("CHANNEL_ID") String channelId,
            @Field("TXN_AMOUNT") String txnAmount,
            @Field("WEBSITE") String website,
            @Field("CALLBACK_URL") String callbackUrl,
            @Field("INDUSTRY_TYPE_ID") String industryTypeId
    );*/

}
