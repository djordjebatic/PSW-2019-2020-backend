package com.example.pswbackend.constants;
import com.example.pswbackend.enums.AppointmentEnum;
import com.example.pswbackend.enums.AppointmentStatus;

import java.time.Month;

/**
 * @author Djordje Batic
 */
public class AppointmentConstants {

    public static final String ADMIN_EMAIL = "admin@gmail.com";

    public static final String ADMIN_PASSWORD = "admin";

    public static final String NEW_CLINIC_NAME = "Bolnica";

    public static final String NEW_CLINIC_ADDRESS = "Balzakova 12";

    public static final String NEW_CLINIC_DESCRIPTION = "Huehuehue";

    public static final String NEW_CLINIC_CITY = "Novi Sad";

    public static final int YEAR = 2020;

    public static final int MONTH = 3;

    public static final Month MONTH_DATE = Month.JANUARY;

    public static final int DAY_OF_MONTH = 20;

    public static final int START_TIME_HOUR = 10;

    public static final int END_TIME_HOUR = 11;

    public static final int MIN = 00;

    public static final int SEC = 00;


    public static final int APPOINTMENT_NO = 3;

    public static final Long APPOINTMENT_ID = 6L;

    public static final Long CLINIC_ID = 1L;


    public static final Long EXAMINATION_6_ID = 6L;

    public static final String DATE = "2020-12-12";

    public static final String DATE_2021 = "01-24-2020 08:00";

    public static final AppointmentEnum OPERATION = AppointmentEnum.OPERATION;

    public static final AppointmentStatus APPOINTMENT_STATUS_APPROVED = AppointmentStatus.APPROVED;

    public static final AppointmentStatus APPOINTMENT_STATUS_AWAITING_APPROVAL = AppointmentStatus.AWAITING_APPROVAL;

    public static final AppointmentStatus APPOINTMENT_STATUS_PREDEF_BOOKED = AppointmentStatus.PREDEF_BOOKED;

    public static final AppointmentStatus APPOINTMENT_STATUS_PREDEF_AVAILABLE = AppointmentStatus.PREDEF_AVAILABLE;

    public static final AppointmentStatus APPOINTMENT_STATUS_CANCELED = AppointmentStatus.CANCELED;


    public static final Integer APPROVED_APPOINTMENTS_IN_TIMESLOT = 2;


    public static final Integer DISCOUNT = 0;

    public static final String SEARCH_START_TIME = "09:00";

    public static final String NEW_NURSE_EMAIL = "sestricica@gmail.com";

    public static final String NEW_NURSE_PASSWORD = "$2y$12$M6EnOZMRiIFt/znBY/C8r.2sglykYnJ0jj2Zm.mGpB3mdG9hD0BeW";

    public static final String NEW_NURSE_FIRST_NAME = "Paja";

    public static final String NEW_NURSE_lAST_NAME = "Pujic";

    public static final String NEW_NURSE_PHONE_NUMBER = "068545334";

    public static final String NEW_NURSE_AUTHORITY = "NURSE";

    public static final String ROOM_LABEL_1 = "VMA";

    public static final Long ORDINATION_1_ID = 1L;

    public static final Long ORDINATION_3_ID = 3L;

    public static final Long CLINIC_ADMIN_ID = 1L;

    public static final Long CLINIC_ADMIN_2_ID = 2L;

    public static final String CLINIC_ADMIN_FIRST_NAME = "Marko";

    public static final String CLINIC_ADMIN_LAST_NAME = "Marković";

    public static final String CLINIC_ADMIN_PHONE_NUMBER = "064153456";

}

