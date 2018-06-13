package cn.edu.hdu.lesstiontest8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements LocationSource {
    //AMap是地图对象
    private AMap aMap;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    public AMapLocationClientOption mLocationClientOption;
    public MapView mapView;
    private boolean isFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = findViewById(R.id.map);
        mapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(true);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
        }
        //开始定位
        location();


    }

    private void location() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationClientOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        mLocationClient.setLocationOption(mLocationClientOption);
        //启动定位client
        mLocationClient.startLocation();

        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
//                        Toast.makeText(getApplicationContext(), amapLocation.getAddress(), Toast.LENGTH_SHORT).show();
                        // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                        if (isFirstLoc) {
                            //设置缩放级别
                            aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
//                            aMap.set
                            //将地图移动到定位点
                            aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                            //点击定位按钮 能够将地图的中心移动到定位点
//                            mLocationListener.onLocationChanged(aMapLocation);

                            LatLng latLng = new LatLng(aMapLocation.getLatitude(),
                                    aMapLocation.getLongitude());
                            //添加图钉
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()));
                            Matrix m=new Matrix();

                            markerOptions.title("当前位置");
                            markerOptions.visible(true);
                            markerOptions.draggable(true);
                            m.postScale(0.3f,0.3f);
                            Bitmap iconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hdu2);
                            iconBitmap=Bitmap.createBitmap(iconBitmap,0,0,iconBitmap.getWidth(),iconBitmap.getHeight(),m,true);
                            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(iconBitmap);
                            markerOptions.icon(bitmapDescriptor);
                            aMap.addMarker(markerOptions);
                            //获取定位信息
                            StringBuffer buffer = new StringBuffer();
                            buffer.append(aMapLocation.getCountry() + ""
                                    + aMapLocation.getProvince() + ""
                                    + aMapLocation.getCity() + ""
                                    + aMapLocation.getProvince() + ""
                                    + aMapLocation.getDistrict() + ""
                                    + aMapLocation.getStreet() + ""
                                    + aMapLocation.getStreetNum());
                            Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                            isFirstLoc = false;
                        }


                        //可在其中解析amapLocation获取相应内容。
                    } else {
                        Toast.makeText(getApplicationContext(), "定位失败", Toast.LENGTH_SHORT).show();
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());

                    }
                }
            }
        };
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
//        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mapView.onSaveInstanceState(outState);
    }
}
