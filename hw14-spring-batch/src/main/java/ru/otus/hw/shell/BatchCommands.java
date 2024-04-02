package ru.otus.hw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


@RequiredArgsConstructor
@ShellComponent
public class BatchCommands {

    private final Job importUserJob;

    private final JobLauncher jobLauncher;

    @SuppressWarnings("unused")
    @ShellMethod(value = "startMigration", key = "s")
    public void startMigrationJobWithJobLauncher() throws Exception {

        JobExecution execution = jobLauncher.run(importUserJob, new JobParametersBuilder()
                .toJobParameters());

        System.out.println(execution);
    }

}