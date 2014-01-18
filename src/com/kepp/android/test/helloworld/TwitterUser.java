package com.kepp.android.test.helloworld;

import com.google.gson.annotations.SerializedName;

public class TwitterUser {

	@SerializedName("screen_name")
	private String screenName;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_image_url")
	private String profileImageUrl;

	public String getProfileImageUrl() {
		return this.profileImageUrl;
	}

	public String getScreenName() {
		return this.screenName;
	}

	public void setProfileImageUrl(final String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public void setScreenName(final String screenName) {
		this.screenName = screenName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}