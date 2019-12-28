package com.hillel.hometasks.task2;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Timeout(time = 1, timeUnit = TimeUnit.MINUTES)
public class CharsCounterTest {
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Setup(Level.Trial)
	public void setPath(){
		setFileName("d:/test.txt");
	}

	public int CountMaxStringLengthWay1(String path) {
		if (IsFileExist(path)) {
			try {
				List<String> linesStream = Files.readAllLines(Paths.get(path));
				return linesStream.stream().mapToInt(String::length).max().getAsInt();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int CountMaxStringLengthWay2(String path) {
		if (IsFileExist(path)) {
			try {
				List<String> linesStream = Files.readAllLines(Paths.get(path));
				return linesStream.stream().filter(s -> !s.isEmpty()).map(String::length).max(Comparator.comparing(Integer::intValue)).get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	private boolean IsFileExist(String path) {
		File file = new File(path);
		return file.exists();
	}
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public void testCountFirst(Blackhole blackhole){
		blackhole.consume(CountMaxStringLengthWay1(fileName));
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	public void testCountSecond(Blackhole blackhole){
		blackhole.consume(CountMaxStringLengthWay2(fileName));
	}

}
