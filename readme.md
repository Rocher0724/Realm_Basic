realm 을 사용해보았다.

---

 - realm init

```
Realm realm; // 외부에서 선언

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // realm 객체를 가져온다. 아마 싱글턴인듯
    Realm.init(this);
    realm = Realm.getDefaultInstance();
}
```
 - realm 데이터 클래스 생성 . RealmObject 를 상속받는다. 또는 implement 하는 방법도 있음. 

```
public class Data extends RealmObject {
  public String q;
  public String a;

  public String getQ() {
    return q;
  }

  public void setQ(String q) {
    this.q = q;
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }
}

```
 - realm save

```
public void realmSave() {
    String A = "A";
   	String B = "B";
	realm.executeTransaction(new Realm.Transaction() {
      	@Override
      	public void execute(Realm realm) {
      	  Data data = realm.createObject(Data.class);
      	  data.setQ(et1.getText().toString());
      	  data.setA(et2.getText().toString());
      	}
    });
}

 ```

  - realm save 2 : 기존에 데이터가 있으면 변경저장, 없으면 새로저장
  - realm에서의 쓰기는 한 transaction 내에서 이뤄져야 하는데 쉬운방법은 realm.executeTransaction 내에서 하는것이다.

```
Data data;

data = realm.where(Data.class)
    .equalTo("q", et1.getText().toString()) // 변수 q 형식에 저장된 것들 중 et1에서 가져온 text와 같은것 중에 첫번째 것만 가져와 data에 담는다.
    .findFirst();

 if (data == null) { // 같은것이 없으면 data가 null이다.
	realm.executeTransaction(new Realm.Transaction() {
 		@Override
 		public void execute(Realm realm) {
    		Data data = realm.createObject(Data.class);
    		data.setQ(et1.getText().toString());
    		data.setA(et2.getText().toString());
  		}
	});
	Toast.makeText(MainActivity.this, "저.장.", Toast.LENGTH_SHORT).show();

} else { // 같은 것이 있으면 그걸 가져와서 A만 바꿔준다. 
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
```

  - realm Read

```
// et3의 text와 같은 q가 있는것을 가져온 뒤 a를 가져온다.
data = realm.where(Data.class)
    .equalTo("q", et3.getText().toString())
    .findFirst();

String A = data.getA();

```