package com.android.example.cameraxbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.example.cameraxbasic.utils.OffsetTable;
import com.android.example.cameraxbasic.utils.ScoreTable;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.qmuiteam.qmui.widget.QMUITopBar;

import com.chaquo.python.Kwarg;
import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;
import com.chaquo.python.Python;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.CLAHE;
import org.opencv.imgproc.Imgproc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class HistoricalDetailActivity extends AppCompatActivity {
    static final String TAG = "PythonOnAndroid";

    private SmartTable tableScore;
    private SmartTable tableOffset;
    private QMUITopBar topbar;
    private ImageView image;
    private TextView date;
    private TextView time;
    private Bitmap bitmap;
    private String initImgPath;
    private String cutImgPath;
    private Boolean fromCamera;
    static {
        System.loadLibrary("opencv_java4");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =getIntent();
        initImgPath=intent.getStringExtra("pic_uri");
        fromCamera = initImgPath != null;
        if(fromCamera){
            cutImgPath = initImgPath.substring(0, initImgPath.length() - 4);
            cutImgPath = cutImgPath + "cut.png";
            //调用图像裁剪算法
            helpme(initImgPath);
        }
        setContentView(R.layout.activity_historical_detail);
        initview();

//        initPython();
//        callPythonCode();
    }
    // 初始化Python环境
    void initPython(){
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }
    // 调用python代码
    void callPythonCode(){
        Python py = Python.getInstance();
        // 调用hello.py模块中的greet函数，并传一个参数
        // 等价用法：py.getModule("hello").get("greet").call("Android");
        py.getModule("hello").callAttr("greet", "Android");

        // 调用python内建函数help()，输出了帮助信息
        py.getBuiltins().get("help").call();

        PyObject obj1 = py.getModule("hello").callAttr("add", 2,3);
        // 将Python返回值换为Java中的Integer类型
        Integer sum = obj1.toJava(Integer.class);
        Log.d(TAG,"add = "+sum.toString());

        // 调用python函数，命名式传参，等同 sub(10,b=1,c=3)
        PyObject obj2 = py.getModule("hello").callAttr("sub", 10,new Kwarg("b", 1), new Kwarg("c", 3));
        Integer result = obj2.toJava(Integer.class);
        Log.d(TAG,"sub = "+result.toString());

        // 调用Python函数，将返回的Python中的list转为Java的list
        PyObject obj3 = py.getModule("hello").callAttr("get_list", 10,"xx",5.6,'c');
        List<PyObject> pyList = obj3.asList();
        Log.d(TAG,"get_list = "+pyList.toString());

        // 将Java的ArrayList对象传入Python中使用
        List<PyObject> params = new ArrayList<PyObject>();
        params.add(PyObject.fromJava("alex"));
        params.add(PyObject.fromJava("bruce"));
        py.getModule("hello").callAttr("print_list", params);
    }

    private void initview(){
        if(fromCamera){
            bitmap = BitmapFactory.decodeFile(cutImgPath);
            Log.i(TAG, "BitmapFactory"+bitmap);
            image = findViewById(R.id.a_his_pic_cut);
//            image.setRotation(270);
            image.setImageBitmap(bitmap);
            SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            String myDate = format.format(new Date());
            date = findViewById(R.id.a_his_date);
            time = findViewById(R.id.a_his_time);
            String mDate = myDate.substring(0,4)+"年"+myDate.substring(5,7)+"月"+myDate.substring(8,10)+"日";
            String mTime = myDate.substring(11);
            date.setText(mDate);
            time.setText(mTime);
        }
//        image.setPivotX(image.getWidth()/2);
//        image.setPivotY(image.getHeight()/2);//支点在图片中心


        topbar = findViewById(R.id.topbar);
        topbar.setTitle("详细评分结果");

        topbar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HistoricalDetailActivity.this, MainActivity.class);
                intent.putExtra("page",1);
                startActivity(intent);
            }
        });

//        List<ScoreTable> listScore = new ArrayList<>();
////        tableScore = findViewById(R.id.a_his_table_score);
//        listScore.add(new ScoreTable("A3",-1, 0, -1, 8));
//        listScore.add(new ScoreTable("A2",0, 0, 0, 10));
//        listScore.add(new ScoreTable("A1",0, 0, 0, 10));
//        listScore.add(new ScoreTable("B1",0, 0, 0, 10));
//        listScore.add(new ScoreTable("B2",0, -1, 0, 9));
//        listScore.add(new ScoreTable("B3",-1, -1, -1, 7));
//        TableConfig tableConfigScore = tableScore.getConfig();
//        tableConfigScore.setMinTableWidth(400);
//        tableConfigScore.setShowXSequence(false);
//        tableConfigScore.setShowYSequence(false);
//        tableConfigScore.setShowTableTitle(false);
//        tableScore.setData(listScore);


        List<OffsetTable> listOffset = new ArrayList<>();
        tableOffset = findViewById(R.id.a_his_table_offset);
        listOffset.add(new OffsetTable("A3","偏远中", "——", "近中倾斜"));
        listOffset.add(new OffsetTable("A2","——", "——", "——"));
        listOffset.add(new OffsetTable("A1","——", "——", "——"));
        listOffset.add(new OffsetTable("B1","——", "——", "——"));
        listOffset.add(new OffsetTable("B2","——", "偏根方", "——"));
        listOffset.add(new OffsetTable("B3","偏远中", "偏切方", "近中倾斜"));
        TableConfig tableConfigOffset = tableOffset.getConfig();
        tableConfigOffset.setMinTableWidth(400);
        tableConfigOffset.setShowXSequence(false);
        tableConfigOffset.setShowYSequence(false);
        tableConfigOffset.setShowTableTitle(false);
        tableOffset.setData(listOffset);
    }

    public void helpme(String filename){

        Mat src = Imgcodecs.imread(filename);
        Mat PreImage=myResize(src,500);
        float ratio = (float)src.cols() / PreImage.rows();
        //高斯滤波
        Mat Gauss=new Mat();
        Imgproc.GaussianBlur(PreImage, Gauss, new Size(9, 9), 0);
//        Imgcodecs.imwrite("E:\\mytest.png",Gauss);
        //自适应直方图均衡化
        Mat PreImageGray=new Mat();
        Imgproc.cvtColor(Gauss, PreImageGray, Imgproc.COLOR_BGR2GRAY);
        CLAHE clahe=Imgproc.createCLAHE();
        Mat res_clahe=new Mat();
        clahe.apply(PreImageGray,res_clahe);
//        Imgcodecs.imwrite("E:\\mytest.png",res_clahe);

        //梯度检测
        Mat sobelx=new Mat();
//        Imgproc.Sobel(PreImageGray,sobelx,6,1,0,3);
//        Core.convertScaleAbs(sobelx,sobelx);
//        Imgcodecs.imwrite("E:\\mytest.png",sobelx);

        Imgproc.Canny(PreImageGray,sobelx,90,255);
//        Imgcodecs.imwrite("E:\\mytest.png",sobelx);

        //中值滤波
        Mat Blur=new Mat();
        Imgproc.medianBlur(sobelx,Blur,3);
//        Imgcodecs.imwrite("E:\\mytest.png",Blur);

        //二值化
        Mat thresh=new Mat();
        Imgproc.threshold(Blur,thresh,90,255,Imgproc.THRESH_BINARY);

//        Imgcodecs.imwrite("E:\\mytest.png",thresh);

        ArrayList<Point> corners=recognizeContours(PreImage,thresh);
        fourPointsTransform(src,corners,ratio,new int[]{7,3},600);

    }

    public Mat myResize(Mat image, double width )
    {
        int OriginWidth = image.cols();
        int OriginHeight = image.rows();

        double ratio = width / OriginWidth;
        double height = ratio * OriginHeight;
        Mat resized = new Mat();
        //System.out.println(width / OriginWidth);
        Imgproc.resize(image, resized, new Size(width,height),0.0,0.0,Imgproc.INTER_AREA);
        return resized;
    }
    public ArrayList<Point> recognizeContours(Mat origin,Mat PreImage){
        //返回角点
        ArrayList<Point> corners=new ArrayList<>();
        //图片轮廓
        List<MatOfPoint> contours=new ArrayList<MatOfPoint>();
        Mat hierarchy = new Mat();
        //查找轮廓
        Imgproc.findContours(PreImage, contours, hierarchy, Imgproc.RETR_EXTERNAL , Imgproc.CHAIN_APPROX_SIMPLE);
        //轮廓上的所有点
        ArrayList<Point> allPoints=new ArrayList<>();

        //拿到点
        for(int i=0;i<contours.size();i++){
            allPoints.add(contours.get(i).toArray()[0]);
            System.out.println(allPoints.get(i));

        }
        int w = PreImage.cols();
        int h = PreImage.rows();
        System.out.println(w);
        System.out.println(h);
        double min1 = Math.pow(w,2) + Math.pow(h,2);
        double min2 = Math.pow(w,2) + Math.pow(h,2);
        double min3 = Math.pow(w,2) + Math.pow(h,2);
        double min4 = Math.pow(w,2) + Math.pow(h,2);
        Point tl=null,bl=null,tr=null,br=null;
        for(int i=0;i<allPoints.size();i++){
            double D2tl=Math.pow(allPoints.get(i).x-0,2)+Math.pow(allPoints.get(i).y-0,2);
            double D2bl=Math.pow(allPoints.get(i).x-0,2)+Math.pow(allPoints.get(i).y-h,2);
            double D2tr=Math.pow(allPoints.get(i).x-w,2)+Math.pow(allPoints.get(i).y-0,2);
            double D2br=Math.pow(allPoints.get(i).x-w,2)+Math.pow(allPoints.get(i).y-h,2);
            if(D2tl < min1){
                min1 = D2tl;
                tl = allPoints.get(i);
            }

            if(D2bl < min2){
                min2 = D2bl;
                bl = allPoints.get(i);
            }

            if(D2tr < min3){
                min3 = D2tr;
                tr = allPoints.get(i);
            }

            if(D2br < min4){
                min4 = D2br;
                br = allPoints.get(i);
            }

        }


        corners.add(tl);
        corners.add(bl);
        corners.add(br);
        corners.add(tr);

        for(int i=0;i<corners.size();i++){
            Imgproc.circle(origin, corners.get(i), 5,new Scalar(255), 5);
            System.out.println(corners.get(i));
        }
//        Imgcodecs.imwrite("E:\\circle.jpg",origin);

        return corners;
    }
    public Mat fourPointsTransform(Mat Origin,ArrayList<Point> Points, double ratio, int[] FinalRatio, int FinalHeight){
        ArrayList<Point> Pts = new ArrayList<>();
        for(int i=0;i<Points.size();i++){
            System.out.println("+++++++++++before+++++++++++");
            System.out.println(Points.get(i));
            Pts.add(new Point((int)(ratio*Points.get(i).x),(int)(ratio*Points.get(i).y)));
            System.out.println("+++++++++++after+++++++++++++");
            System.out.println(Pts.get(i));
        }
        MatOfPoint2f dst = new MatOfPoint2f(
                new Point(0, 0),
                new Point((int)FinalHeight*FinalRatio[0]/FinalRatio[1] - 1, 0),
                new Point((int)FinalHeight*FinalRatio[0]/FinalRatio[1] - 1, FinalHeight - 1),
                new Point(0, FinalHeight - 1)
        );


        Point tl=Pts.get(0);
        Point bl=Pts.get(1);
        Point br=Pts.get(2);
        Point tr=Pts.get(3);

        MatOfPoint2f rect = new MatOfPoint2f(
                tl,tr,br,bl
        );

        Mat M = Imgproc.getPerspectiveTransform(rect, dst);
        Mat warped=new Mat(600,1400, CvType.CV_8UC3);
        Imgproc.warpPerspective(Origin, warped,M,new Size((int)(FinalHeight*FinalRatio[0]/FinalRatio[1]), FinalHeight));
        Imgcodecs.imwrite(cutImgPath,warped);
        return warped;
    }

}