package com.rajhack4.homeautomation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by dbot_5 on 19-03-2018.
 */

public class TvGuideFragment extends Fragment{
    WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tv_guide_fragment,container,false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(R.layout.tv_listing_action_bar);
        mWebView = (WebView)view.findViewById(R.id.tvGuideWebView);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mWebView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('page-head')[0].style.display=\"none\"; " +
                        "document.getElementsByClassName('showcase_centeralign-990')[0].style.display=\"none\"; " +
                        "})()");
            }

            public boolean shouldOverrideUrlLoading (WebView view, String url){
                //view.loadDataWithBaseURL(url,text,"text/html",null,null);
                //True if the host application wants to leave the current WebView and handle the url itself, otherwise return false.
                return false;
            }
        });

        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.loadUrl("https://www.tatasky.com/tvguiderv/tv-guide.jsp");
        //webView.loadUrl("https://www.tatasky.com/tvguiderv/tv-guide.jsp");
        return view;
    }
}
