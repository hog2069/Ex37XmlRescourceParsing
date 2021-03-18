package com.hog2020.ex37xmlrescourceparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv =findViewById(R.id.tv);
    }

    public void clickBtn(View view) {
        //xml 폴더에 있는 xml 문서를 읽어서 분석(Parse) 하는 작업수행
        //res 폴더 창고관리자 객체 소환
        Resources res=getResources();

        //창고 관리자로부터 파서객체 얻어오기
        XmlResourceParser xrp= res.getXml(R.xml.movies);

        //xml 파서에게 분석작업 요청
        try {
            xrp.next();
            int eventType =xrp.getEventType();

            StringBuffer buffer =new StringBuffer();

            while(eventType!=XmlResourceParser.END_DOCUMENT){

                switch (eventType){
                    case XmlResourceParser.START_DOCUMENT:
                        buffer.append("xml 파싱을 시작합니다.\n\n");
                        break;
                    case XmlResourceParser.START_TAG:
                        String tagName=xrp.getName();
                        if(tagName.equals("item")){

                        }else if(tagName.equals("no")){
                            buffer.append("번호:");
                        }else  if(tagName.equals("title")){
                            buffer.append("제목:");
                        }else if(tagName.equals("genre")){
                            buffer.append("장르:");
                        }
                        break;
                    case XmlResourceParser.TEXT:
                        String text = xrp.getText();
                        buffer.append(text);
                        break;
                    case XmlResourceParser.END_TAG:
                        String tagName2= xrp.getName();
                        if(tagName2.equals("item")){
                            buffer.append("------------------\n");
                        }else if(tagName2.equals("no")){
                            buffer.append("\n");
                        }else if(tagName2.equals("title")){
                            buffer.append("\n");
                        }else if(tagName2.equals("genre")){
                            buffer.append("\n");
                        }
                        break;
                    case XmlResourceParser.END_DOCUMENT:
                }

                eventType=xrp.next();
            }
            buffer.append("\n\n 파싱을 완료했습니다\n");
            tv.setText(buffer.toString());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}