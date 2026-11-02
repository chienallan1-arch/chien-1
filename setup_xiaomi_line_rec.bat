@echo off
chcp 65001 >nul
echo ========================================================
echo   小米 (Xiaomi 14 Ultra / HyperOS / MIUI)
echo   LINE 通話自動錄音 - 免 Root 電腦端一鍵安裝與授權工具
echo ========================================================
echo.
echo [1/4] 正在檢查 ADB 手機連線狀態...
adb devices
echo.
if exist app-debug.apk (
    echo [2/4] 偵測到同目錄下的 app-debug.apk，正在為您自動安裝至手機...
    adb install -r app-debug.apk
) else (
    echo [2/4] 提示：若已安裝「LINE 通話錄音」APK，將直接進行權限設定...
)
echo.
echo [3/4] 正在啟用小米內建錄音程式「隱藏入口 (應用通話錄音 app_rec)」...
adb shell settings put system call_record_app_support 1
adb shell settings put secure miui_app_call_record_enabled 1
echo.
echo [4/4] 正在授權「LINE 通話錄音」App 免 Root 自動觸發與雙向收音權限...
adb shell pm grant com.example.lineautorecorder android.permission.RECORD_AUDIO
adb shell appops set com.example.lineautorecorder RECORD_AUDIO allow
adb shell cmd notification allow_listener com.example.lineautorecorder/.LineCallMonitorService
echo.
echo ========================================================
echo   設定完成！現在您可以拔除 USB 線。
echo   當 LINE 通話接通約 3 秒後將自動錄音，掛斷自動停止！
echo   錄音檔位置：手機內部儲存空間/MIUI/sound_recorder/app_rec/
echo ========================================================
pause