package com.effortguy.design_patterns.templateCallback;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer calcSum(String filePath) throws IOException {

        //lambda
        //LineCallback sumCallback = (LineCallback<Integer>) (line, value) -> value + Integer.valueOf(line);

        LineCallback sumCallback = new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.valueOf(line);
            }
        };

        return lineReadTemplate(filePath, sumCallback, 0);
    }

    public Integer calcMultiply(String filePath) throws IOException {

        //lambda
        //LineCallback multiplyCallback = (LineCallback<Integer>) (line, value) -> value * Integer.valueOf(line);

        LineCallback multiplyCallback = new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value * Integer.valueOf(line);
            }
        };

        return lineReadTemplate(filePath, multiplyCallback, 1);
    }

    public String concatenate(String filepath) throws IOException {

        //lambda
        //LineCallback<String> concatenateCallback = (line, value) -> value + line;

        LineCallback<String> concatenateCallback =
                new LineCallback<String>() {
                    @Override
                    public String doSomethingWithLine(String line, String value) {
                        return value + line;
                    }
                };

        return lineReadTemplate(filepath, concatenateCallback, "");
    }

    public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            int ret = callback.doSomethingWithReader(br);

            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public <T> T lineReadTemplate(String filePath, LineCallback<T> lineCallback, T intVal) throws IOException {

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));

            T res = intVal;
            String line = null;

            while((line = br.readLine()) != null) {
                res = lineCallback.doSomethingWithLine(line, res);
            }

            return res;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
