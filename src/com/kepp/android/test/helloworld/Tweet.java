package com.kepp.android.test.helloworld;

import com.google.gson.annotations.SerializedName;

public class Tweet {

	@SerializedName("created_at")
	private String DateCreated;

	@SerializedName("id")
	private String Id;

	@SerializedName("text")
	private String Text;

	@SerializedName("in_reply_to_status_id")
	private String InReplyToStatusId;

	@SerializedName("in_reply_to_user_id")
	private String InReplyToUserId;

	@SerializedName("in_reply_to_screen_name")
	private String InReplyToScreenName;

	@SerializedName("user")
	private TwitterUser User;

	public String getDateCreated() {
		return this.DateCreated;
	}

	public String getId() {
		return this.Id;
	}

	public String getInReplyToScreenName() {
		return this.InReplyToScreenName;
	}

	public String getInReplyToStatusId() {
		return this.InReplyToStatusId;
	}

	public String getInReplyToUserId() {
		return this.InReplyToUserId;
	}

	public String getText() {
		return this.Text;
	}

	public void setDateCreated(final String dateCreated) {
		this.DateCreated = dateCreated;
	}

	public void setId(final String id) {
		this.Id = id;
	}

	public void setInReplyToScreenName(final String inReplyToScreenName) {
		this.InReplyToScreenName = inReplyToScreenName;
	}

	public void setInReplyToStatusId(final String inReplyToStatusId) {
		this.InReplyToStatusId = inReplyToStatusId;
	}

	public void setInReplyToUserId(final String inReplyToUserId) {
		this.InReplyToUserId = inReplyToUserId;
	}

	public void setText(final String text) {
		this.Text = text;
	}

	public void setUser(final TwitterUser user) {
		this.User = user;
	}

	public TwitterUser getUser() {
		return this.User;
	}

	@Override
	public String toString() {
		return this.getText();
	}
}