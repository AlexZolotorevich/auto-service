package by.htp.entity;

import java.io.Serializable;

public class News implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long ID;
	private String title;
	private String text;
	private String date;
	private int userID;
	
	
	public News(String title, String text, String date) {
		this.title = title;
		this.text = text;
		this.date = date;
	}
	
	/** create in DAO*/
	public News(long ID, String title, String text, String date, int userID) {
		this.ID = ID;
		this.title = title;
		this.text = text;
		this.date = date;
		this.userID = userID;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public long getID() {
		return ID;
	}
	public void setID(long ID) {
		this.ID = ID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + userID;
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
		News other = (News) obj;
		if (ID != other.ID)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "News [ID=" + ID + ", title=" + title + ", text=" + text + ", date=" + date + ", userID=" + userID + "]";
	}

}
