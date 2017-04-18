package com.project.salem.model;

public class Schedule implements Comparable<Schedule> {
	
	private int id;
	private String time;
	
	public Schedule(int id, String time) {
		super();
		this.id = id;
		this.time = time;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (id != other.id)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", time=" + time + "]";
	}

	@Override
	public int compareTo(Schedule s) {
		return time.compareTo(s.getTime());
	}
	
}
