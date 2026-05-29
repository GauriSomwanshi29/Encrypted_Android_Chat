package com.example.syntecxhub_encrypted_android_chat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText messageInput;
    Button sendButton;
    TextView encryptedText;
    TextView decryptedText;
    TextView historyText;

    StringBuilder messageHistory =
            new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        messageInput =
                findViewById(R.id.messageInput);

        sendButton =
                findViewById(R.id.sendButton);

        encryptedText =
                findViewById(R.id.encryptedText);

        decryptedText =
                findViewById(R.id.decryptedText);

        historyText =
                findViewById(R.id.historyText);

        sendButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        String message =
                                messageInput
                                        .getText()
                                        .toString()
                                        .trim();

                        if (message.isEmpty()) {

                            Toast.makeText(
                                    MainActivity.this,
                                    "Enter a message",
                                    Toast.LENGTH_SHORT
                            ).show();

                            return;
                        }

                        try {

                            String encryptedMessage =
                                    AESHelper.encrypt(message);

                            String decryptedMessage =
                                    AESHelper.decrypt(encryptedMessage);

                            encryptedText.setText(
                                    "Encrypted Message:\n\n"
                                            + encryptedMessage
                            );

                            decryptedText.setText(
                                    "Decrypted Message:\n\n"
                                            + decryptedMessage
                            );

                            messageHistory.append(
                                    "You: "
                            ).append(message).append("\n");

                            historyText.setText(
                                    "Message History:\n\n"
                                            + messageHistory
                            );

                            messageInput.setText("");

                        } catch (Exception e) {

                            Toast.makeText(
                                    MainActivity.this,
                                    "Encryption Error",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                });
    }
}