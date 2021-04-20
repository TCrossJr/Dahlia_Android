package com.example.dahlia_android.ui.user;

import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.groups.Groups;
import com.example.dahlia_android.ui.home.Feed;
import com.example.dahlia_android.ui.messages.Messages;

public class UserProfile {

    private String bannerURL;
    private String thumbnailURL;
    private String displayName;
    private String username; // email
    private String location;
    private String date_created;
    private String date_of_birth;
    private String town;
    private String state;
    private String zip_code;
    private String description;
    private int followingCount;
    private int followerCount;
    private Feed feed;
    private FriendsList friends;
    private Groups groups;
    private Messages messages;
    //private AuPairNearBy nearby; // if notEnabled then isEmpty


    public UserProfile(String bannerURL, String thumbnailURL, String displayName, String username, String location, String date_created, String date_of_birth, String town, String state, String zipCode, String description, int followingCount, int followerCount, Feed feed, FriendsList friends, Groups groups, Messages messages) {
        this.bannerURL = bannerURL;
        this.thumbnailURL = thumbnailURL;
        this.displayName = displayName;
        this.username = username;
        this.location = location;
        this.date_created = date_created;
        this.date_of_birth = date_of_birth;
        this.town = town;
        this.state = state;
        this.zip_code = zipCode;
        this.description = description;
        this.followingCount = followingCount;
        this.followerCount = followerCount;
        this.feed = feed;
        this.friends = friends;
        this.groups = groups;
        this.messages = messages;
    }

    public UserProfile(UserProfile profile) {
        this.bannerURL = profile.bannerURL;
        this.thumbnailURL = profile.thumbnailURL;
        this.displayName = profile.displayName;
        this.username = profile.username;
        this.location = profile.location;
        this.date_created = profile.date_created;
        this.followingCount = profile.followingCount;
        this.followerCount = profile.followerCount;
        this.date_of_birth = profile.date_of_birth;
        this.town = profile.town;
        this.state = profile.state;
        this.zip_code = profile.zip_code;
        this.description = profile.description;
        this.feed = profile.feed;
        this.friends = profile.friends;
        this.groups = profile.groups;
        this.messages = profile.messages;
    }

    public UserProfile(UserProfile profile, String test) {
        this.bannerURL = profile.bannerURL;
        this.thumbnailURL = profile.thumbnailURL;
        this.displayName = profile.displayName;
        this.username = profile.username;
        this.location = profile.location;
        this.date_created = profile.date_created;
        this.followingCount = profile.followingCount;
        this.followerCount = profile.followerCount;
        this.date_of_birth = profile.date_of_birth;
        this.town = profile.town;
        this.state = profile.state;
        this.zip_code = profile.zip_code;
        this.description = profile.description;
        this.feed = profile.feed;
        this.friends = profile.friends;
        this.groups = profile.groups;
        this.messages = profile.messages;
    }

    public UserProfile(String date_of_birth, String town, String state, String zipCode, String description) {
        this.date_of_birth = date_of_birth;
        this.town = town;
        this.state = state;
        this.zip_code = zipCode;
        this.description = description;
        this.feed = new Feed();
        this.friends = new FriendsList();
        this.groups = new Groups();
        this.messages = new Messages();
    }

    public UserProfile(String username, String displayName, String date_of_birth, String town, String state, String zipCode, String description, Feed feed, FriendsList friends, Groups groups, Messages messages) {
        this.username = username;
        this.displayName = displayName;
        this.date_of_birth = date_of_birth;
        this.town = town;
        this.state = state;
        this.zip_code = zipCode;
        this.description =description;
        this.feed = feed;
        this.friends = friends;
        this.groups = groups;
        this.messages = messages;
    }

    public String getBannerURL() {
        return bannerURL;
    }

    public void setBannerURL(String bannerURL) {
        this.bannerURL = bannerURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public FriendsList getFriends() {
        return friends;
    }

    public void setFriends(FriendsList friends) {
        this.friends = friends;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }
}
