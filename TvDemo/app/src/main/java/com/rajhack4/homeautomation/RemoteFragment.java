package com.rajhack4.homeautomation;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

import static com.rajhack4.homeautomation.ChannelsDbUtils.getChannelThroughVoice;
import static com.rajhack4.homeautomation.IRPattern.CH_MINUS_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.CH_PLUS_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.POWER_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.VOL_PLUS_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.MUTE_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.VOL_MINUS_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.ZERO_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.ONE_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.TWO_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.THREE_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.FOUR_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.FIVE_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.SIX_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.SEVEN_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.EIGHT_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.NINE_PATTERN;
import static com.rajhack4.homeautomation.IRPattern.SELECT_PATTERN;

/**
 * Created by dbot_5 on 19-03-2018.
 */

public class RemoteFragment extends Fragment {
    private static final Pattern channelPattern = Pattern.compile("channel");
    private static final Pattern volumePattern = Pattern.compile("volume");
    private static final Pattern volumeUpPattern = Pattern.compile("volume up");
    private static final Pattern volumeDownPattern = Pattern.compile("volume down");
    private static final Pattern nextChannelPattern = Pattern.compile("next channel");
    private static final Pattern previousChannelPattern = Pattern.compile("previous channel");
    private static final Pattern channelNumPattern = Pattern.compile("[a-z]*\\d{0,3}");
    private static final Pattern powerPattern = Pattern.compile("power");
    ImageButton channelUpButton,channelDownButton;
    Button num1Button,num2Button,num3Button,num4Button,num5Button,num6Button,num7Button,num8Button,num9Button,num0Button;
    ImageButton volUpButton,volDownButton;
    ImageButton voiceSearchButton,muteButton;
    Button selectButton;
    RelativeLayout num_layout;
    ConsumerIrManager irManager;

    private final int REQ_CODE_SPEECH_INPUT = 1001;
    private static final int TATASKY_FREQUENCY = 36000;
    private final String LOG_CAT = RemoteFragment.class.getName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.remote_fragment, container, false);
        irManager = (ConsumerIrManager) getActivity().getSystemService(Context.CONSUMER_IR_SERVICE);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(R.layout.remote_action_bar);
        if (!irManager.hasIrEmitter()) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setMessage("No IR transmitter Found");
            alertDialogBuilder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            getActivity().getSupportFragmentManager().popBackStack();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
        channelUpButton = (ImageButton) view.findViewById(R.id.btn_ch_plus);
        channelDownButton = (ImageButton) view.findViewById(R.id.btn_ch_minus);
        volUpButton = (ImageButton) view.findViewById(R.id.btn_vol_plus);
        volDownButton = (ImageButton) view.findViewById(R.id.btn_vol_minus);
        voiceSearchButton = (ImageButton) view.findViewById(R.id.btn_mic);
        num_layout = (RelativeLayout)view.findViewById(R.id.relativeLayout_num);
        num0Button = (Button)view.findViewById(R.id.btn_0);
        num1Button = (Button)view.findViewById(R.id.btn_1);
        num2Button = (Button)view.findViewById(R.id.btn_2);
        num3Button = (Button)view.findViewById(R.id.btn_3);
        num4Button = (Button)view.findViewById(R.id.btn_4);
        num5Button = (Button)view.findViewById(R.id.btn_5);
        num6Button = (Button)view.findViewById(R.id.btn_6);
        num7Button = (Button)view.findViewById(R.id.btn_7);
        num8Button = (Button)view.findViewById(R.id.btn_8);
        num9Button = (Button)view.findViewById(R.id.btn_9);
        muteButton = (ImageButton)view.findViewById(R.id.btn_mute);
        selectButton = (Button)view.findViewById(R.id.btn_n_ok);

        channelUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChannelUp();
            }
        });
        channelDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setChannelDown();
            }
        });
        volUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVolumeUp();
            }
        });
        volDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVolumeDown();
            }
        });
        voiceSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });
        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMute();
            }
        });
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSelect();
            }
        });

        num0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnum0Button();
            }
        });
        num1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnum1Button();
            }
        });
        num2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnum2Button();
            }
        });
        num3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnum3Button();
            }
        });
        num4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnum4Button();
            }
        });
        num5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnum5Button();
            }
        });
        num6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnum6Button();
            }
        });
        num7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnum7Button();
            }
        });
        num8Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnum8Button();
            }
        });
        num9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setnum9Button();
            }
        });
        return view;
    }

    private void setChannelUp() {
        irManager.transmit(36000, CH_PLUS_PATTERN);
        Log.e(LOG_CAT,"ChannelUp");
    }
    private void toggleMute() {
        irManager.transmit(36000, MUTE_PATTERN);
        Log.e(LOG_CAT,"mute_button toggled");
    }
    private void enterSelect() {
        irManager.transmit(36000, SELECT_PATTERN);
        Log.e(LOG_CAT,"select button entered");
    }

    private void setChannelDown() {
        irManager.transmit(36000, CH_MINUS_PATTERN);
        Log.e(LOG_CAT,"ChannelDown");
    }
    private void setnum0Button(){
        irManager.transmit(36000, ZERO_PATTERN);
        Log.e(LOG_CAT,"ZeroButton");
    }
    private void setnum1Button(){
        irManager.transmit(36000, ONE_PATTERN);
        Log.e(LOG_CAT,"OneButton");
    }
    private void setnum2Button(){
        irManager.transmit(36000, TWO_PATTERN);
        Log.e(LOG_CAT,"TwoButton");
    }
    private void setnum3Button(){
        irManager.transmit(36000, THREE_PATTERN);
        Log.e(LOG_CAT,"ThreeButton");
    }
    private void setnum4Button(){
        irManager.transmit(36000, FOUR_PATTERN);
        Log.e(LOG_CAT,"FourButton");
    }
    private void setnum5Button(){
        irManager.transmit(36000, FIVE_PATTERN);
        Log.e(LOG_CAT,"FiveButton");
    }
    private void setnum6Button(){
        irManager.transmit(36000, SIX_PATTERN);
        Log.e(LOG_CAT,"SixButton");
    }
    private void setnum7Button(){
        irManager.transmit(36000,SEVEN_PATTERN);
        Log.e(LOG_CAT,"SevenButton");
    }
    private void setnum8Button(){
        irManager.transmit(36000,EIGHT_PATTERN);
        Log.e(LOG_CAT,"EightButton");

    }
    private void setnum9Button(){
        irManager.transmit(36000, NINE_PATTERN);
        Log.e(LOG_CAT,"NineButton");
    }
    private void setVolumeUp() {
        irManager.transmit(36000, VOL_PLUS_PATTERN);
        Log.e(LOG_CAT,"VolumeUp");
    }
    private void setVolumeDown() {
        irManager.transmit(36000, VOL_MINUS_PATTERN);
        Log.e(LOG_CAT,"VolumeDown");
    }
    private void setChannel(String s){
        for(int i=0;i< s.length();i++){
            if(s.charAt(i) == '1')
                setnum1Button();
            else if(s.charAt(i) == '2')
                setnum2Button();
            else if(s.charAt(i) == '3')
                setnum3Button();
            else if(s.charAt(i) == '4')
                setnum4Button();
            else if(s.charAt(i) == '5')
                setnum5Button();
            else if(s.charAt(i) == '6')
                setnum6Button();
            else if(s.charAt(i) == '7')
                setnum7Button();
            else if(s.charAt(i) == '8')
                setnum8Button();
            else if(s.charAt(i) == '9')
                setnum9Button();
            else if(s.charAt(i) == '0')
                setnum0Button();

        }

    }

    private void togglePower(){
        irManager.transmit(36000, POWER_PATTERN);
        Log.e(LOG_CAT,"TogglePowerButton");
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == getActivity().RESULT_OK && null != data) {
                    Log.e(LOG_CAT,"Speech Finished");
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.e(LOG_CAT,"sheldon cooper"+result.get(0));
                    sendCommandThroughSpeech(result.get(0).toLowerCase());
                }
                break;
            }

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.remote_fragment_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.btn_power:
                togglePower();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendCommandThroughSpeech(String speechInput){
        if(speechInput.contains("channel up"))
            setChannelUp();
        if(speechInput.contains("channel down"))
            setChannelDown();

        if(speechInput.contains("volume up"))
            setVolumeUp();
        if(speechInput.contains("volume down"))
            setVolumeDown();

        if(speechInput.contains("power"))
            togglePower();

        if(speechInput.contains("channel number") || speechInput.contains("go to") || (speechInput.matches("[0-9]+") && speechInput.length() > 2))
            setChannel(speechInput);
        if(speechInput.contains("go to"))
        {
            int channelNo = getChannelThroughVoice(getActivity(), speechInput.replace("go to", "").trim());
            String temp = Integer.toString(channelNo);
            setChannel(temp);
        }
    }
}
