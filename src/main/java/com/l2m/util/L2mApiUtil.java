package com.l2m.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.l2m.domain.base.enums.ItemEnum;
import com.l2m.model.SearchDto;
import com.l2m.util.global.IsNullUtil;

/**
 * 리니지2M API util
 * 
 * by jaewon
 */
public class L2mApiUtil {

  // api key
  // 재원쓰 key
  // private static final String API_KEY = "eyJraWQiOiI2YWFmYzEzZi1hMGJjLTQ1YjYtYTUyMS00YTAyMGUzMTljYWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOiI3NENCQzMyNS1BREI0LUUyMTEtOEU2OS1FNjFGMTM1RTk5MkYifQ.BvePhxTN3fUAL2QquvVPxw7GmdOwzwxvC-s5XgelVUafPe7MLqI0L9HEsA8pYqEXZN5jeOIUB3NUKnA6g7pfl3p_T9w_zKhFM_5HPUfHoOdB01tbsUOWdUzhpsDguSo_xRzl2BVSBW56gZiod-ruOh1nmEZx6oFNYLY8bpVcgU5oF4kdO5M1jltv1hu_vpGbtTWAtJzndmJyFL02h0CmHfrluvzazDJ6pKMVKEEovrZmvz9iA-82j2ZaKPBRrMoDElpNuaWUVSl_WWi1yQU3pxYWm_jtVZ6lYraZvZL5RLGx-Nxai2iYvIx-UQZCb9vmk9iA5fWIY5LFO_9Dot5KPw";
  // 구현쓰 key
  private static final String API_KEY = "eyJraWQiOiIwMjFhZmJhNC01ZTc1LTQ5MDQtYTljZC05OTk5NWMzODY4NzEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOiIyRTE5Q0MxQi01MDU2LTRFQjAtQTEyOS00NDYzOUFEQTJGM0EifQ.cPbEP8ghI5svseTcVnXmJkKPbkuZK5Rd2MC3fLeEKeFLHXuBfDx4FLEvQJWby3F3gpcGcj6XooXW9y1RD684cWFPXNzqlFdPMQ6HCLBmuD_B-LuTGhJJ_AmHdLpPu-vH2UqFqGkZgJ9sHhTweMTDuQDt90xWpHSolNsJlbNrsT8eLNgorazJ1D6YVFVX-NUxqgJx8Oc5jodUnP5NW3mJbPQhzrv72ar3lRdwY7uvPKRgoW-8FdYMfkLSNArcRMMbN40FGIFSVewVlDfYpFNAKUApvicOwoax75a4yxkV_6Z41J_t7w6WN6dXDhy1FrrOumLoTBw_c9TTZQk74eFJFQ";
  // base url
  private static final String BASE_URL = "https://dev-api.plaync.com/l2m/v1.0/market/items/";

  /**
   * 아이템 리스트 조회
   * @throws IOException
   */
  public static List<SearchDto.itemListInfo> getItemList(Integer serverId, String itemName, Integer enchantLevel, ItemEnum itemEnum) throws IOException {
    StringBuilder urlBuilder = new StringBuilder(BASE_URL + "search"); // API URL
    SearchDto.l2mApiItemList itemList = new SearchDto.l2mApiItemList();
    urlBuilder.append("?server_id=" + serverId); // API QueryString
    // 아이템타입이 무기일때만 강화수치 적용
    if (itemEnum.equals(ItemEnum.WEAPON)) {
      urlBuilder.append("&from_enchant_level=" + enchantLevel); // API QueryString
    }
    urlBuilder.append("&search_keyword=" + URLEncoder.encode(itemName, "UTF-8")); // API QueryString
    urlBuilder.append("&sale=" + true); // API QueryString
    
    URL url = new URL(urlBuilder.toString());

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    conn.setRequestProperty("Authorization", "Bearer " + API_KEY); // 발급받은 API Key 값

    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) { // Response Code가 200인 경우
      /*
       * 응답값을 한 줄씩 출력하는 예제 입니다.
       * 다른 방식으로 결과를 사용하기 원할 경우 json 으로 내려온 응답을 parsing 해서 사용하시면 됩니다.
       * 응답값은 “문서” 메뉴 하위의 API Schemas 를 참고하세요.
       */

      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuffer response = new StringBuffer();

      String readLine;
      while ((readLine = in.readLine()) != null) {
        response.append(readLine);
      }

      Gson gson = new GsonBuilder().serializeNulls().create();

      itemList = gson.fromJson(response.toString(), SearchDto.l2mApiItemList.class);

      in.close();
    } else {
      // do nothing
    }
    conn.disconnect();

    return itemList.getContents();
  }

  /**
   * 아이템 정보 조회
   * @throws IOException
   */
  public static SearchDto.l2mApiItemInfo getItemInfo(Integer itemId, Integer enchantLevel) throws IOException {
    StringBuilder urlBuilder = new StringBuilder(BASE_URL + itemId); // API URL
    SearchDto.l2mApiItemInfo itemInfo = new SearchDto.l2mApiItemInfo();
    if (!IsNullUtil.check(enchantLevel)) 
      urlBuilder.append("?enchant_level=" + enchantLevel); // API QueryString
    
    URL url = new URL(urlBuilder.toString());

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    conn.setRequestProperty("Authorization", "Bearer " + API_KEY); // 발급받은 API Key 값

    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) { // Response Code가 200인 경우
      /*
       * 응답값을 한 줄씩 출력하는 예제 입니다.
       * 다른 방식으로 결과를 사용하기 원할 경우 json 으로 내려온 응답을 parsing 해서 사용하시면 됩니다.
       * 응답값은 “문서” 메뉴 하위의 API Schemas 를 참고하세요.
       */

      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuffer response = new StringBuffer();

      String readLine;
      while ((readLine = in.readLine()) != null) {
        response.append(readLine);
      }

      Gson gson = new GsonBuilder().serializeNulls().create();

      itemInfo = gson.fromJson(response.toString(), SearchDto.l2mApiItemInfo.class);

      in.close();
    } else {
      // do nothing
    }
    conn.disconnect();

    return itemInfo;
  }

  public static SearchDto.l2mApiPriceInfo getPriceInfo(Integer itemId, Integer serverId, Integer enchantLevel) throws IOException {
    StringBuilder urlBuilder = new StringBuilder(BASE_URL + itemId + "/price"); // API URL
    SearchDto.l2mApiPriceInfo itemPriceInfo = new SearchDto.l2mApiPriceInfo();

    if (!IsNullUtil.check(enchantLevel) & !IsNullUtil.check(serverId)) {
      urlBuilder.append("?server_id=" + serverId);
      urlBuilder.append("&enchant_level=" + enchantLevel); // API QueryString
    }
    
    URL url = new URL(urlBuilder.toString());

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    conn.setRequestProperty("Authorization", "Bearer " + API_KEY); // 발급받은 API Key 값

    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) { // Response Code가 200인 경우
      /*
       * 응답값을 한 줄씩 출력하는 예제 입니다.
       * 다른 방식으로 결과를 사용하기 원할 경우 json 으로 내려온 응답을 parsing 해서 사용하시면 됩니다.
       * 응답값은 “문서” 메뉴 하위의 API Schemas 를 참고하세요.
       */

      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuffer response = new StringBuffer();

      String readLine;
      while ((readLine = in.readLine()) != null) {
        response.append(readLine);
      }

      Gson gson = new GsonBuilder().serializeNulls().create();

      itemPriceInfo = gson.fromJson(response.toString(), SearchDto.l2mApiPriceInfo.class);

      in.close();
    } else {
      // do nothing
    }
    conn.disconnect();

    return itemPriceInfo;
  }
}
