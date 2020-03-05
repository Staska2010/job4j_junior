package io;

import java.io.*;

/**
 * Server availability analyzer based on it's logs
 */
public class Analyzer {
    /**
     * Analyze log file
     * @param source - path to log file
     * @param target - path to analysis results file
     */
    public void unavailable(String source, String target) {
        String resultOfAnalysis = analyzeLog(source);
        recordResult(target, resultOfAnalysis);
    }

    /**
     * Analyse lof file line by line
     * if "400" or "500" state condition is followed by "200" or "300" state condition
     * then record appropriate time interval
     * @param source
     * @return
     */
    private String analyzeLog(String source) {
        String line;
        StringBuilder sb = new StringBuilder();
        boolean isAvailable = true;
        try (BufferedReader bf = new BufferedReader(new FileReader(source))) {
            while ((line = bf.readLine()) != null) {
                if ((line.startsWith("400") || line.startsWith("500")) && isAvailable == true) {
                    sb.append(line.split("\\s")[1]).append("; ");
                    isAvailable = false;
                }
                if ((line.startsWith("200") || line.startsWith("300")) && isAvailable == false) {
                    sb.append(line.split("\\s")[1]).append(System.lineSeparator());
                    isAvailable = true;
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException();
        }
        return sb.toString();
    }

    /**
     * Record time interval of server unavailability to
     * target file
     * @param target - path to results file
     * @param result - time interval for recording
     */
    private void recordResult(String target, String result) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(target))) {
            pw.print(result);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
}
