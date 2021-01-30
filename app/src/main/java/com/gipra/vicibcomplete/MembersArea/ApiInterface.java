package com.gipra.vicibcomplete.MembersArea;



import com.gipra.vicibcomplete.MembersArea.Complaints.ResponseComplaintsImageUpload;
import com.gipra.vicibcomplete.MembersArea.Complaints.ResponseComplaintsList;
import com.gipra.vicibcomplete.MembersArea.District.ResponseDistrict;
import com.gipra.vicibcomplete.MembersArea.Gene.ResponsePremiumGenealogy;
import com.gipra.vicibcomplete.MembersArea.Gene.ResponseStandardGenealogy;
import com.gipra.vicibcomplete.MembersArea.MyProfile.ResponseBankProofUpload;
import com.gipra.vicibcomplete.MembersArea.MyProfile.ResponseEditProfile;
import com.gipra.vicibcomplete.MembersArea.MyProfile.ResponseImageUpload;
import com.gipra.vicibcomplete.MembersArea.MyProfile.ResponseImageView;
import com.gipra.vicibcomplete.MembersArea.MyProfile.ResponsePanUpload;
import com.gipra.vicibcomplete.MembersArea.Payout.ResponsePayoutLedger;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.ResponsePremiumListLeftSideSales;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.ResponsePremiumTeamSalesBVMatching;
import com.gipra.vicibcomplete.MembersArea.PremiumPlanReports.ResponsePremiumTeamSalesBonusDetails;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.MyproductDate;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.ResponseMyProductsBill;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.ResponseMyProductsDate;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.ResponseMyproductsDateOnly;
import com.gipra.vicibcomplete.MembersArea.Reports.ResponseBasicActiveMemebers;
import com.gipra.vicibcomplete.MembersArea.Reports.ResponseFirstPurchaseBVReport;
import com.gipra.vicibcomplete.MembersArea.Reports.ResponseLeftSideMembers;
import com.gipra.vicibcomplete.MembersArea.Reports.Mem_MyProducts.ResponseMyProducts;
import com.gipra.vicibcomplete.MembersArea.Reports.ResponseSponsorsList;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.ResponseDownlineRepurchaseDetails;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.ResponseRepurchaseBvReport;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.ResponseRepurchaseIncome;
import com.gipra.vicibcomplete.MembersArea.RepurchasePlanReports.ResponseRepurchaseIncomeDetails;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.ResponseStandardLeftSideSales;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.ResponseStandardTeamSalesBVMatching;
import com.gipra.vicibcomplete.MembersArea.StandardPlanReports.ResponseStandardTeamSalesBonusDetails;
import com.gipra.vicibcomplete.MembersArea.State.ResponseState;
import com.gipra.vicibcomplete.MembersArea.ui.Dashboard.Responsedashboard;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("Login")
    Call<ResponseLogin>Login(@Field("username") String username,
                             @Field("password") String password);

    @POST("State_list")
    Call<ResponseState>loadstatelist();

    @FormUrlEncoded
    @POST("District_list")
    Call<ResponseDistrict>loaddistrictlist(@Field("state_name") String sname);





    @FormUrlEncoded
    @POST("Login_check")
    Call<ResponseLoginCheck>LoginCheck(@Field("username") String username);

    @FormUrlEncoded
    @POST("Logout")
    Call<ResponseLogout>Logout(@Field("username") String username);

    @FormUrlEncoded
    @POST("My_products")
    Call<ResponseMyProducts>searchmyproducts(@Field("userid") int userid,
                                             @Field("fromdate") String fromdate,
                                             @Field("todate") String todate);

    @FormUrlEncoded
    @POST("Myproduct_date")
    Call<ResponseMyProductsDate>searchmyproductsdate(@Field("userid") int userid,
                                                 @Field("fromdate") String fromdate);

    @FormUrlEncoded
    @POST("Myproduct_date_only")
    Call<ResponseMyproductsDateOnly>SearchMyproductsDateonly(@Field("userid") int userid,
                                                             @Field("fromdate") String fromdate,
                                                             @Field("todate") String todate);

    @FormUrlEncoded
    @POST("Myproducts_bill")
    Call<ResponseMyProductsBill>MyproductsBill(@Field("userid") int userid,
                                                     @Field("orderid") String orderid);




    @FormUrlEncoded
    @POST("First_purchase_bv_report")
    Call<ResponseFirstPurchaseBVReport>SearchFirstPurchase(@Field("userid") int userid,
                                                           @Field("fromdate") String fromdate,
                                                           @Field("todate") String todate);



    @FormUrlEncoded
    @POST("Sponsors_list")
    Call<ResponseSponsorsList>searchsponsorlist(@Field("userid") int userid);

    @FormUrlEncoded
    @POST("Registration")
    Call<ResponseRegistration>Register(@Field("c_name") String c_name,
                                       @Field("c_firm_name") String c_firm_name,
                                       @Field("d_dob") String d_dob,
                                       @Field("C_MOBILE") String C_MOBILE,
                                       @Field("c_address") String c_address,
                                       @Field("n_pincode") String n_pincode,
                                       @Field("c_country") String c_country,
                                       @Field("C_STATE")String C_STATE,
                                       @Field("C_DISTRICT") String C_DISTRICT,
                                       @Field("C_PANCHAYAT") String C_PANCHAYAT,
                                       @Field("c_nominee_name") String c_nominee_name,
                                       @Field("c_relation") String c_relation,
                                       @Field("c_sponsor_name") String c_sponsor_name,
                                       @Field("c_upline") String c_upline,
                                       @Field("c_position") String c_position,
                                       @Field("c_password") String c_password,
                                       @Field("CONFIRM_PASSWORD") String CONFIRM_PASSWORD,
                                       @Field("c_email_id") String c_email_id,
                                       @Field("c_username") String c_username,
                                       @Field("C_BANK") String C_BANK,
                                       @Field("C_BRANCH") String C_BRANCH,
                                       @Field("C_ACC_NO") String C_ACC_NO,
                                       @Field("C_PAN") String C_PAN,
                                       @Field("C_IFC_CODE") String C_IFC_CODE);

    @FormUrlEncoded
    @POST("Myteam_left_search_list")
    Call<ResponseLeftSideMembers>SearchLeftSidemembers(@Field("id") int userid,
                                                       @Field("from_date") String fromdate,
                                                       @Field("to_date") String todate,
                                                       @Field("side") String side);

    @FormUrlEncoded
    @POST("Left_side_sales")
    Call<ResponseStandardLeftSideSales>SearchStandardLeftSales(@Field("userid") int userid,
                                                               @Field("from_date") String fromdate,
                                                               @Field("to_date") String todate,
                                                               @Field("side") String side);
    @FormUrlEncoded
    @POST("Team_sales_bv_matching")
    Call<ResponseStandardTeamSalesBVMatching>SearchStandardTeamSalesBV(@Field("userid") int userid,
                                                                       @Field("from_date") String fromdate,
                                                                       @Field("to_date") String todate);

    @FormUrlEncoded
    @POST("Team_sales_bonus_details")
    Call<ResponseStandardTeamSalesBonusDetails>SearchStandardTeamSalesBonus(@Field("userid") int userid,
                                                                            @Field("from_date") String fromdate,
                                                                            @Field("to_date") String todate);

    @FormUrlEncoded
    @POST("Left_side_sale_gold")
    Call<ResponsePremiumListLeftSideSales>SearchPremiumLeftSales(@Field("userid") int userid,
                                                                 @Field("from_date") String fromdate,
                                                                 @Field("to_date") String todate,
                                                                 @Field("side") String side);
    @FormUrlEncoded
    @POST("Team_sales_bv_matching_gold")
    Call<ResponsePremiumTeamSalesBVMatching>SearchPremiumTeamSalesBV(@Field("userid") int userid,
                                                                     @Field("from_date") String fromdate,
                                                                     @Field("to_date") String todate);

    @FormUrlEncoded
    @POST("Team_sales_bonus_details_gold")
    Call<ResponsePremiumTeamSalesBonusDetails>SearchPremiumTeamSalesBonus(@Field("userid") int userid,
                                                                          @Field("from_date") String fromdate,
                                                                          @Field("to_date") String todate);

    @FormUrlEncoded
    @POST("Repurchase_bv_report")
    Call<ResponseRepurchaseBvReport>SearchRepurchaseBVReport(@Field("userid") int userid,
                                                             @Field("from_date") String fromdate,
                                                             @Field("to_date") String todate);

    @FormUrlEncoded
    @POST("Downline_repurchase_details")
    Call<ResponseDownlineRepurchaseDetails>SearchDownlineRepurchase(@Field("userid") int userid,
                                                                    @Field("from_date") String fromdate,
                                                                    @Field("to_date") String todate);

    @FormUrlEncoded
    @POST("Repurchase_income")
    Call<ResponseRepurchaseIncome>SearchRepurchaseIncome(@Field("userid") int userid,
                                                         @Field("from_date") String fromdate,
                                                         @Field("to_date") String todate);

    @FormUrlEncoded
    @POST("Repurchase_income_details")
    Call<ResponseRepurchaseIncomeDetails>SearchRepurchaseIncomeDetails(@Field("userid") int userid,
                                                                       @Field("from_date") String fromdate,
                                                                       @Field("to_date") String todate);

    @FormUrlEncoded
    @POST("Payout_ledger")
    Call<ResponsePayoutLedger>SearchPayoutLedger(@Field("id") int id,
                                                 @Field("from_date") String fromdate,
                                                 @Field("to_date") String todate);

    @FormUrlEncoded
    @POST("Basic_active_members_list")
    Call<ResponseBasicActiveMemebers>SearchBasicActive(@Field("id") int id,
                                                        @Field("from_date") String fromdate,
                                                        @Field("to_date") String todate);


    @FormUrlEncoded
    @POST("Complaint_list")
    Call<ResponsePayoutLedger>SearchPayoutLedger(@Field("id") int id);


    @FormUrlEncoded
    @POST("Dashboard_view")
    Call<Responsedashboard>SearchDashboard(@Field("userid") int userid);

    @Multipart
    @POST("profile_picture_upload")
    Call<ResponseImageUpload>ImageUpload(@Part("c_profile_photo\";filename=\"myfile.jpg\"") RequestBody file,
                                         @Part("member_id") int member_id);

    @FormUrlEncoded
    @POST("profile_image_view")
    Call<ResponseImageView>ViewPhoto(@Field("user_id") int user_id);






//    @Multipart
//    @POST("User_image_upload")
//    Call<ResponseUserImage>userimageupload(@Part("c_profile_photo\";filename=\"myfile.jpg\"") RequestBody file,
//                                           @Part("member_id") int memberid);



    @FormUrlEncoded
    @POST("Change_password")
    Call<ResponseChangePassword>Changepsd(@Field("userid") String userid,
                                          @Field("current_password") String currentpsd,
                                          @Field("new_password") String newpsd);


    @FormUrlEncoded
    @POST("Gold_api_geneology")
    Call<ResponsePremiumGenealogy>PremiumGene(@Field("userid") int userid);


    @FormUrlEncoded
    @POST("Silver_geneology1")
    Call<ResponseStandardGenealogy>StandardGene(@Field("userid") int userid);


    @FormUrlEncoded
    @POST("profile_update")
    Call<ResponseEditProfile>UpdateProf(@Field("id") int id,
                                        @Field("C_FNAME") String C_FNAME,
                                        @Field("C_GENDER") String C_GENDER,
                                        @Field("c_dob") String c_dob,
                                        @Field("c_mobile") String c_mobile,
                                        @Field("c_email") String c_email,
                                        @Field("c_address") String c_address,
                                        @Field("c_country") String c_country,
                                        @Field("c_state") String c_state,
                                        @Field("C_DISTRICT") String C_DISTRICT,
                                        @Field("C_PANCHAYATH") String C_PANCHAYATH,
                                        @Field("c_zipcode") String c_zipcode,
                                        @Field("n_pancard") String n_pancard,
                                        @Field("C_BANK") String C_BANK,
                                        @Field("C_BRANCH") String C_BRANCH,
                                        @Field("C_ACC_NO") String C_ACC_NO,
                                        @Field("C_IFC_CODE") String C_IFC_CODE,
                                        @Field("c_nominee_name") String c_nominee_name,
                                        @Field("c_relation") String c_relation);

    @Multipart
    @POST("Bankproof_uploading")
    Call<ResponseBankProofUpload>BankProof(@Part("C_ACCOUNT_PROOF\";filename=\"myfile.jpg\"") RequestBody file,
                                                 @Part("member_id") int member_id);

    @Multipart
    @POST("PAN_uploading")
    Call<ResponsePanUpload>PanUpload(@Part("C_PANCARD_FILE\";filename=\"myfile.jpg\"") RequestBody file,
                                     @Part("member_id") int member_id);



    @FormUrlEncoded
    @POST("Complaint_list")
    Call<ResponseComplaintsList>ComplaintsList(@Field("id") int id,
                                                   @Field("from_date") String fromdate,
                                                   @Field("to_date") String todate);
    @Multipart
    @POST("Complaint_image_upload")
    Call<ResponseComplaintsImageUpload>CompImageUpload(@Part("c_profile_photo\";filename=\"myfile.jpg\"") RequestBody file,
                                                   @Part("member_id") int member_id);






}
