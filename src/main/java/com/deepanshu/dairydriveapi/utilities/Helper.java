package com.deepanshu.dairydriveapi.utilities;

import com.deepanshu.dairydriveapi.configs.AppConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Helper {
    private static final String emailBodyTemplate = "<div style=\"background-color: transparent; padding-right: 10px; padding-left: 10px; display: block;\"><div style=\"background-color: #ffffff; border-color: #e5e5e5; border-style: solid; border-width: 0 1px 1px 1px; max-width: 600px; width: 100%%; margin: 60.5px auto 10px auto; border-top: solid 3px #8e2de2; text-align: center; padding: 30px 0;\"><h1 style=\"padding-bottom: 5px; color: #000; font-family: Poppins, Helvetica, Arial, sans-serif; font-size: 28px; font-weight: 400; line-height: 36px;\">Hello, %s!</h1><h2 style=\"margin-bottom: 30px; color: #999; font-family: Poppins, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 300;\">Please verify Your Email Account</h2><p style=\"font-size: 14px; margin: 0px 21px; color: #666; font-family: 'Open Sans', Helvetica, Arial, sans-serif; font-weight: 300; line-height: 22px; margin-bottom: 40px;\">Thanks for creating your account on our <span style=\"color: #4c4c4c; font-weight: 600;\">DairyDrive</span>. Please click the confirm button to validate your account.</p><a style=\"display: inline-block; background: linear-gradient(to right, #8e2de2, #4a00e0); border: none; font-family: Poppins, Helvetica, Arial, sans-serif; font-weight: 200; letter-spacing: 1px; text-transform: uppercase; text-decoration: none; padding: 10px 20px; font-size: 18px; color: white; border-radius: 5px;\" href=\"%s\" type=\"button\">Confirm Email</a><hr style=\"border-width: 4px; border-radius: 50%%; width: 56%%; display: block; margin: 20px auto;\"><p style=\"color: #a2a2a2; font-size: 12px; line-height: 17px; font-style: italic;\">If you did not sign up for this account you can ignore this email and the account will be deleted.</p></div><p style=\"margin: 10px auto; width:fit-content\">© by DairyDrive.com</p></div>";

    private static AppConfigurations appConfig;

    @Autowired
    public Helper(AppConfigurations appConfig){
        Helper.appConfig = appConfig;
    }

    public static String getLinkForEmailVerification(String emailToken){
        return appConfig.getBaseUrl() + "/api/account/email-verification?token="+emailToken;
    }
    public static String getBodyForEmailVerification(String receiverName, String emailToken){

        String emailLink = getLinkForEmailVerification(emailToken);
        return String.format(emailBodyTemplate, receiverName,emailLink);
   }
}
