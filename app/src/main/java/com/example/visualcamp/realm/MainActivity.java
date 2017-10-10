package com.example.visualcamp.realm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

  EditText et1, et2, et3;
  Button btn, btn2;
  Realm realm;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    et1 = (EditText) findViewById(R.id.et1);
    et2 = (EditText) findViewById(R.id.et2);
    et3 = (EditText) findViewById(R.id.et3);
    btn = (Button) findViewById(R.id.button);
    btn2 = (Button) findViewById(R.id.button2);
    btn.setOnClickListener(clickListener);
    btn2.setOnClickListener(clickListener);
    Realm.init(this);
    realm = Realm.getDefaultInstance();

  }

  View.OnClickListener clickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      Data data;
      switch (view.getId()) {
        case R.id.button:
          // begin 으로 시작해서 commit으로 끝나는 작업이 executeTransaction과 동일하다.
//          realm.beginTransaction();
//          Data data = realm.createObject(Data.class);
//          data.setQ(et1.getText().toString());
//          data.setA(et2.getText().toString());
//          realm.commitTransaction();

          // 기존에 있는 데이터인지 확인
          data = realm.where(Data.class)
            .equalTo("q", et1.getText().toString())
            .findFirst();

          if (data == null) {
            realm.executeTransaction(new Realm.Transaction() {
              @Override
              public void execute(Realm realm) {
                Data data = realm.createObject(Data.class);
                data.setQ(et1.getText().toString());
                data.setA(et2.getText().toString());
              }
            });
            Toast.makeText(MainActivity.this, "저.장.", Toast.LENGTH_SHORT).show();
          } else {
            realm.executeTransaction(new Realm.Transaction() {
              @Override
              public void execute(Realm realm) {
                Data data = realm.where(Data.class)
                    .equalTo("q", et1.getText().toString())
                    .findFirst();
                data.setA(et2.getText().toString());
              }
            });
            Toast.makeText(MainActivity.this, "변.경.저.장.", Toast.LENGTH_SHORT).show();
          }


          break;
        case R.id.button2:
          Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
//          RealmResults<Data> result = realm.where(Data.class)
//              .equalTo("q", et3.getText().toString())
//              .findAll();

          data = realm.where(Data.class)
            .equalTo("q", et3.getText().toString())
            .findFirst();

          if (data == null) {
            Toast.makeText(MainActivity.this, "해당하는 답변이 없습니다.", Toast.LENGTH_SHORT).show();
          } else {
            String answer = data.getA();

            intent.putExtra("answer", answer);
            startActivity(intent);
          }
          break;
      }
    }
  };
}
