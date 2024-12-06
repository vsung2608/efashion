package com.example.e_fashion.util;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JiraIssueCreator {
    private static final String JIRA_URL = "https://e-fashion.atlassian.net";
    private static final String API_ENDPOINT = "/rest/api/3/issue";
    private static final String USERNAME = "vsung2608@gmail.com";
    private static final String API_TOKEN = "ATATT3xFfGF0d5ZApArzJqIzdRoBbYbOWpgkI4Fcm9v0BKEerzoTAHhvZ82iRqOfQP-lAybATC68owQUY32E4ppEqi5ZzGAEERvCK9-mgpAP0ZVq3ddLDQZWM3XCZKaG47tXzxqO-etdHw55FXiJUxPCaMldyGSvBzCr9AS50cqNGt_Op3NvpQA=ABA4CCBD";

    public static void automationCreateIssue(String operatingSystem, String browser, String version, String status, String result, String summary){
        try {
            String description = "Description:\n" +
                    "\tOperating System: " + operatingSystem + "\n" +
                    "\tBrowser: " + browser + "," + version + "\n" +
                    "\tTest Environment: Personal computer with 8GB RAM, Intel i5 CPU\n" +
                    "\tTest Steps:\n" +
                    "\t- Launch the application on " + browser + " browser.\n" +
                    "\t- Click the login button on navigation bar\n" +
                    "\t- Enter the admin's account\n" +
                    "\t- Click login button\n" +
                    "\t- Redirect to admmin's dasboard page\n" +
                    "\tExpected Results: The features should function as expected without any issues.\n" +
                    "\n" +
                    "Test Results:\n" +
                    "\t" + status + ": The application functions " + result + " work as expected on " + operatingSystem + " with " + browser + ".\n" +
                    "\tNo issues found.\n";

            createIssue("XSP", summary, description, "Test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createIssue(String projectKey, String summary, String descriptionText, String issueType) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(JIRA_URL + API_ENDPOINT);

        String auth = USERNAME + ":" + API_TOKEN;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        post.setHeader("Authorization", "Basic " + encodedAuth);
        post.setHeader("Content-Type", "application/json");

        JSONObject fields = new JSONObject();
        fields.put("summary", summary);

        JSONObject description = new JSONObject();
        description.put("type", "doc");
        description.put("version", 1);

        JSONArray contentArray = new JSONArray();
        JSONObject paragraph = new JSONObject();
        paragraph.put("type", "paragraph");

        JSONArray paragraphContent = new JSONArray();
        JSONObject text = new JSONObject();
        text.put("type", "text");

        String encodedDescriptionText = new String(descriptionText.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        text.put("text", encodedDescriptionText);

        paragraphContent.put(text);
        paragraph.put("content", paragraphContent);
        contentArray.put(paragraph);
        description.put("content", contentArray);

        fields.put("description", description);

        JSONObject project = new JSONObject();
        project.put("key", projectKey);
        fields.put("project", project);

        JSONObject issueTypeObj = new JSONObject();
        issueTypeObj.put("name", issueType);
        fields.put("issuetype", issueTypeObj);


        JSONObject payload = new JSONObject();
        payload.put("fields", fields);

        StringEntity entity = new StringEntity(payload.toString());
        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println("Response Status: " + response.getStatusLine());
        System.out.println("Response Body: " + responseBody);

        client.close();
    }
}

