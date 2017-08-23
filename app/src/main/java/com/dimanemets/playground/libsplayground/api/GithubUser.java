package com.dimanemets.playground.libsplayground.api;

/**
 * Created by dimanemets on 22/08/2017.
 *
 * Example:
     {
         "login": "dimanem",
         "id": 7033764,
         "avatar_url": "https://avatars1.githubusercontent.com/u/7033764?v=4",
         "gravatar_id": "",
         "url": "https://api.github.com/users/dimanem",
         "html_url": "https://github.com/dimanem",
         "followers_url": "https://api.github.com/users/dimanem/followers",
         "following_url": "https://api.github.com/users/dimanem/following{/other_user}",
         "gists_url": "https://api.github.com/users/dimanem/gists{/gist_id}",
         "starred_url": "https://api.github.com/users/dimanem/starred{/owner}{/repo}",
         "subscriptions_url": "https://api.github.com/users/dimanem/subscriptions",
         "organizations_url": "https://api.github.com/users/dimanem/orgs",
         "repos_url": "https://api.github.com/users/dimanem/repos",
         "events_url": "https://api.github.com/users/dimanem/events{/privacy}",
         "received_events_url": "https://api.github.com/users/dimanem/received_events",
         "type": "User",
         "site_admin": false,
         "name": "Dima Nemets",
         "company": "StartApp",
         "blog": "https://www.linkedin.com/profile/view?id=134350158&trk=nav_responsive_tab_profile",
         "location": "Ramat Gan, Israel",
         "email": null,
         "hireable": null,
         "bio": null,
         "public_repos": 3,
         "public_gists": 2,
         "followers": 4,
         "following": 1,
         "created_at": "2014-03-22T20:07:34Z",
         "updated_at": "2017-08-11T12:40:16Z"
     }
 *
 */

public class GithubUser {
    private String login;
    private long id;
    private String url;

    public String getLogin() {
        return login;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
