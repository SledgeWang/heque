package cn.com.timbrian.interfaces.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import cn.com.timbrian.interfaces.StudentRepository;
import cn.com.timbrian.vo.VoStudent;

public class InMemoryStudentServiceImpl implements StudentRepository{

	private AtomicInteger currentInteger = new AtomicInteger();
	private ConcurrentMap<Integer,	VoStudent> toDos = new ConcurrentHashMap<>();
	
	@Override
	public List<VoStudent> findAll() {
		List<VoStudent> studentList = new ArrayList<VoStudent>(toDos.values());
		Collections.sort(studentList);
		return studentList;
	}

	@Override
	public VoStudent findById(Integer id) {
		return toDos.get(id);
	}

	@Override
	public Integer insert(VoStudent student) {
		Integer id = currentInteger.incrementAndGet();
		student.setId(id);
		toDos.putIfAbsent(id, student);
		return id;
	}

	@Override
	public void update(VoStudent student) {
		toDos.replace(student.getId(), student);
	}

	@Override
	public void delete(VoStudent student) {
		
		toDos.remove(student.getId());
	}

}
