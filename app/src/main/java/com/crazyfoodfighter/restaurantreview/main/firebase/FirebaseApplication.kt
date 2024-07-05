package com.crazyfoodfighter.restaurantreview.main.firebase

import android.app.Application
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.elvishew.xlog.printer.AndroidPrinter
import com.crazyfoodfighter.restaurantreview.main.settings.LoggerConfig
import com.google.firebase.database.FirebaseDatabase

/**
 * Firebase 및 XLog 초기화를 담당하는 Custom Application 클래스.
 *
 * 이 클래스는 Firebase Realtime Database의 오프라인 지속성 활성화와
 * XLog 라이브러리 초기화를 담당합니다.
 *
 * Firebase 오프라인 지속성 기능이 정상적으로 작동하려면, 이 클래스를
 * AndroidManifest.xml 파일에 명시해야 합니다.
 *
 * @see <a href="https://stackoverflow.com/questions/37753991/com-google-firebase-database-databaseexception-calls-to-setpersistenceenabled">StackOverflow: Calls to setPersistenceEnabled</a>
 */
class FirebaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Firebase Realtime Database의 오프라인 지속성 활성화
        initFirebaseDatabase()

        // XLog 초기화
        initXLog()

        // 기타 초기화 작업
        LoggerConfig.initialize()
    }

    /**
     * Firebase Realtime Database를 오프라인 지속성 기능을 활성화하여 초기화합니다.
     *
     * 예외를 피하기 위해 FirebaseDatabase 인스턴스를 생성하기 전에 호출해야 합니다.
     */
    private fun initFirebaseDatabase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }

    /**
     * XLog 초기화 설정.
     *
     * 태그, 스레드 정보, 스택 트레이스, 경계 및 로그 레벨을 포함한 로깅 구성을 설정합니다.
     */
    private fun initXLog() {
        val config = LogConfiguration.Builder()
            .tag("반월당 Log")
            .enableThreadInfo()
            .enableStackTrace(2)
            .enableBorder()
            .logLevel(LogLevel.ALL)  // 로그 레벨 설정
            .build()

        val androidPrinter = AndroidPrinter()
        XLog.init(config, androidPrinter)
    }
}
