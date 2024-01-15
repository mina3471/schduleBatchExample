package com.example.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Scheduled(cron = "* 40 4 * * ?")
    public void create_csv(){
        List<TaskDTO> taskDTOs = taskMapper.read_data();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
        String formattedNow = LocalDateTime.now().format(formatter);
        try(
                FileOutputStream fileOutputStream = new FileOutputStream( formattedNow+"통계.csv");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        ){
            bufferedWriter.write("언어,총조회수");
            bufferedWriter.write("\n");
            for(TaskDTO taskDTO: taskDTOs){
                bufferedWriter.write(taskDTO.getLanguage());
                bufferedWriter.write(",");
                bufferedWriter.write(taskDTO.getTotalCount());
                bufferedWriter.write("\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            System.out.println("파일 생성 완료");
            taskMapper.truncate_data();

        }catch (Exception e){
            System.out.println("오류 발생"+ e);
        }




    }

}
