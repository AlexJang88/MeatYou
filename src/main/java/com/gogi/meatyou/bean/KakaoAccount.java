package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class KakaoAccount {

	private boolean profile_needs_agreement;
	private boolean profile_nickname_needs_agreement;
	private boolean profile_image_needs_agreement;
	private boolean name_needs_agreement;
	private String name;
	private boolean email_needs_agreement;
	private boolean is_email_valid;
	private boolean is_email_verified;
	private String email;
	private boolean age_range_needs_agreement;
	private String age_range;
	private boolean birthyear_needs_agreement;
	private String birthyear;
	private boolean birthday_needs_agreement;
	private String birthday;
	private String birthday_type;
	private boolean gender_needs_agreement;
	private String gender;
	private boolean phone_number_needs_agreement;
	private String phone_number;
	private boolean ci_needs_agreement;
	private String ci;
	private Date ci_authenticated_at;
	
	
	
	
	
	
}
