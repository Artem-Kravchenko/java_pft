package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static com.jayway.jsonpath.JsonPath.read;

public class TestBase {

  public boolean isIssueOpen(int issueId) throws IOException { // Проверка статуса Issue
    String status = getIssueStatusById(1046);
    if (status.equals("resolved") || status.equals("closed")) {
      return false;
    } else {
      System.out.println("Issue is NOT fixed yet");
      return true;
    }
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public Executor getExecutor() {
    return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
  }

  private String getIssueStatusById(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/"+issueId+".json")).returnContent().asString();
    List<String> status = read(json, "$.issues[*].state_name");
    return status.get(0);
  }



}




