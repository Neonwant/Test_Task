package com.softgroup.test;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String fileRandom = "C:\\Users\\user\\Desktop\\test\\random.txt"; // if you don't need random words, comment this
        String filePathDestiny = "C:\\Users\\user\\Desktop\\test\\split.txt";
        String dirTempFiles = "C:\\Users\\user\\Desktop\\test\\tmp\\";
        Integer topPhrase = 50;

        //fillFile(fileRandom);     // fill random words for testing
        splitPhrase(fileRandom, filePathDestiny);     // split phrases
        String sortFilePath = sortFile(filePathDestiny, dirTempFiles); // sort file
        topPhrase(sortFilePath, topPhrase);     // top phrase save in the file in descending
    }


    private static void fillFile(String random) {
        try (Writer writer = new BufferedWriter(new FileWriter(random))) {
            Random r = new Random();
//            for (int i = 1; i < 2_000_001; ++i) { //2500Mb
      //     for (int i = 1; i < 1_000_001; ++i) { //1220Mb
            for (int i = 1; i < 10_001; ++i) { //13Mb
                for (int j = 0; j < 50; ++j) {
                    writer.write("слово_");
                    writer.write(Integer.toString(i));
                    writer.write('_');
                    for (int k = 7 + r.nextInt(5); k > 0; --k) {
                        writer.write('a' + r.nextInt(25));
                    }
                    writer.write('|');
                }
                writer.write('\n');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void splitPhrase(String filePath, String filePathDestiny) {
        try {
            long start = System.currentTimeMillis();
            File file_source = new File(filePath);
            FileReader fr = new FileReader(file_source);
            BufferedReader reader = new BufferedReader(fr);

            File file_destiny = new File(filePathDestiny);
            FileWriter fw = new FileWriter(file_destiny);
            BufferedWriter writer = new BufferedWriter(fw);

            String line = reader.readLine();

            while (line != null) {
                String[] str = line.split("\\|");
                for (int i = 0; i < str.length; i++) {
                    writer.write(str[i] + "\n");
                }

                line = reader.readLine();
            }

            reader.close();
            writer.close();

            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;
            System.out.println(timeConsumedMillis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String sortFile(String filePath, String dirTempFiles) {
        try {

            long start = System.currentTimeMillis();
            int i = 0;
            List<String> list = new ArrayList<String>();

            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            String s = br.readLine();

            while (s != null) {
                list.add(s);
                if (list.size() == 70000) {
                    Collections.sort(list);
                    FileWriter fw = new FileWriter(new File(dirTempFiles + "temp-" + i + ".txt"));
                    for (String x : list) {
                        fw.write(x);
                        fw.write("\n");
                    }
                    fw.close();
                    i++;
                    list = new ArrayList<String>();
                }
                s = br.readLine();
            }

            br.close();

            FileWriter fw = new FileWriter(new File(dirTempFiles + "temp-" + i + ".txt"));
            Collections.sort(list);
            for (String x : list) {
                fw.write(x);
                fw.write("\n");
            }
            fw.close();

            Map<String, Integer> map = new TreeMap<String, Integer>();

            BufferedReader[] brArr = new BufferedReader[i + 1];
            for (int j = 0; j <= i; j++) {
                brArr[j] = new BufferedReader(new FileReader(new File(dirTempFiles + "temp-" + j + ".txt")));
                map.put(brArr[j].readLine(), j);
            }


            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dirTempFiles + "output.txt")));

            String endofline = "\n";
            while (!map.isEmpty()) {
                s = map.keySet().iterator().next();
                i = map.get(s);
                map.remove(s);
                bw.write(s);
                bw.write(endofline);
                s = brArr[i].readLine();
                if (s != null && s != "") {
                    map.put(s, i);
                }
            }
            bw.close();

            for (int j = 0; j < brArr.length; j++) {
                brArr[j].close();
                new File(dirTempFiles + "temp-" + j + ".txt").delete(); // delete temp files
            }

            new File(filePath).delete(); // delete split file

            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;
            System.out.println(timeConsumedMillis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dirTempFiles + "output.txt";
    }
    private static void topPhrase(String filePath, Integer topPhrase) {
        try {
            long start = System.currentTimeMillis();

            Map<String, Integer> map = new TreeMap<String, Integer>();
            BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
            String s = br.readLine();
            String prevS = "";

            int i = 0;

            while (s != null) { // count of occurrences of all phrases
                if (prevS.compareTo(s) == 0) {
                    ++i;
                    map.replace(s, i);
                }
                else if (i == 0) {
                    ++i;
                    map.put(s, i);
                    prevS = s;
                }
                else {
                    i = 1;
                    map.put(s, i);
                    prevS = s;
                }
                s = br.readLine();
            }
            Map sortedMap = sortByValues(map); // sorting
            map = null;

            Collection<String> collection = sortedMap.keySet();

            i = 0;

            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath)));
            for (String key : collection) {
                if (i >= topPhrase) break;
                else {
                    i++;
                    bw.write(key);
                    bw.write("\n");
                }
            }

            br.close();
            bw.close();



            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;
            System.out.println(timeConsumedMillis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <K, V extends Comparable<V>> Map<K, V>    // for sorting based on values
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k1, K k2) {
                        int compare =
                                map.get(k2).compareTo(map.get(k1));
                        if (compare == 0)
                            return 1;
                        else
                            return compare;
                    }
                };

        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
}