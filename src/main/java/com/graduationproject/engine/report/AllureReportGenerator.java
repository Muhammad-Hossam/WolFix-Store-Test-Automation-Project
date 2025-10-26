package com.graduationproject.engine.report;

import com.graduationproject.engine.OSUtils;
import com.graduationproject.engine.TerminalUtils;
import com.graduationproject.engine.TimeManager;
import com.graduationproject.engine.logs.LogsManager;
import org.apache.commons.io.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.graduationproject.engine.dataReader.PropertyReader.getProperty;
import static com.graduationproject.engine.report.AllureConstants.*;

public class AllureReportGenerator {

    //Generate Allure report
    //--single-file
    public static void generateReports(boolean isSingleFile) {
        Path outputFolder = isSingleFile ? AllureConstants.REPORT_PATH : AllureConstants.FULL_REPORT_PATH;
        // allure generate -o reports --single-file --clean
        List<String> command = new ArrayList<>(List.of(
                AllureBinaryManager.getExecutable().toString(),
                "generate",
                AllureConstants.RESULTS_FOLDER.toString(),
                "-o", outputFolder.toString(),
                "--clean"
        ));
        if (isSingleFile) command.add("--single-file");
        TerminalUtils.executeTerminalCommand(command.toArray(new String[0]));
    }

    //rename report file to AllureReport_timestamp.html
    public static String renameReport(){
        String newFileName = AllureConstants.REPORT_PREFIX + TimeManager.getTimestamp()+AllureConstants.REPORT_EXTENSION;
        com.graduationproject.FileUtils.renameFile(AllureConstants.REPORT_PATH.resolve(AllureConstants.INDEX_HTML).toString(),newFileName);
        return newFileName;
    }


    //open Allure report in browser
    public static void openReport(String reportFileName){
        if(!getProperty("executionType").toLowerCase().contains("local")) return;
        Path reportPath = AllureConstants.REPORT_PATH.resolve(reportFileName);
        switch (OSUtils.getCurrentOS()){
            case WINDOWS -> TerminalUtils.executeTerminalCommand("cmd.exe","/c","start " + reportPath.toString());
            case MAC, LINUX-> TerminalUtils.executeTerminalCommand("open", reportPath.toString());
            default -> LogsManager.warn("OS not supported to open Allure report");
        }
    }

    //copy history folder to results folder
    public static void copyHistory() {
        try {
            FileUtils.copyDirectory(HISTORY_FOLDER.toFile(), RESULTS_HISTORY_FOLDER.toFile());
        } catch (Exception e) {
            LogsManager.error("Error copying history files", e.getMessage());
        }
    }
}
