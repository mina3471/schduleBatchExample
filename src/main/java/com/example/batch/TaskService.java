package com.example.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskMapper taskMapper;

    @Scheduled(cron = "* * * * * ?")
    public void create_csv(){
        List<TaskDTO> taskDTOs = taskMapper.read_data();

        try(
                FileOutputStream fileOutputStream = new FileOutputStream("my_csv.csv");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "MS949");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        ){
            for(TaskDTO taskDTO: taskDTOs){
                bufferedWriter.write(taskDTO.get시군구명());
                bufferedWriter.write(",");
                bufferedWriter.write(taskDTO.getTotalCount());
                bufferedWriter.write("\n");
            }
            System.out.println("파일 생성 완료");

        }catch (Exception e){
            System.out.println("오류 발생"+ e);
        }




    }

}
