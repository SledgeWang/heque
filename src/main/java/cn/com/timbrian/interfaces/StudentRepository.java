package cn.com.timbrian.interfaces;

import java.util.List;

import cn.com.timbrian.vo.VoStudent;

public interface StudentRepository {

	List<VoStudent> findAll();
	
	VoStudent findById(Integer id);
	
	Integer insert(VoStudent student);
	
	void update(VoStudent student);
	
	void delete(VoStudent student);
}
