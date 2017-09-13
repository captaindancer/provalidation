package com.dancer.provalidation.db;

import java.util.Date;

public class Booklist {

	private long id;//主键
	private long owner_id;//书单主ID
	private String book_list_name;//书单名
	private String book_list_description;//书单简介
	private String owner_description;//书单主简介
	private String label;//标签
	private int type;
	private long collect_count;//关注数
	private long comment_count;//评论数
	private int del_flag;//删除标识位
	private int check_flag;//审核标识位
	private int show_flag;//展示标识位
	private Date create_time;//创建时间
	private Date update_time;//更新时间，只有添加书时更新
	private Date show_time;//展示时间
	private String operator;//操作人
	private Date operate_time;//操作时间
	private int book_add_count;//当日添加作品数
	private long book_count;//添加作品数
	private Date fresh_time;//更新时间，书单有任何操作都更新
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(long owner_id) {
		this.owner_id = owner_id;
	}
	public String getBook_list_name() {
		return book_list_name;
	}
	public void setBook_list_name(String book_list_name) {
		this.book_list_name = book_list_name;
	}
	public String getBook_list_description() {
		return book_list_description;
	}
	public void setBook_list_description(String book_list_description) {
		this.book_list_description = book_list_description;
	}
	public String getOwner_description() {
		return owner_description;
	}
	public void setOwner_description(String owner_description) {
		this.owner_description = owner_description;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getCollect_count() {
		return collect_count;
	}
	public void setCollect_count(long collect_count) {
		this.collect_count = collect_count;
	}
	public long getComment_count() {
		return comment_count;
	}
	public void setComment_count(long comment_count) {
		this.comment_count = comment_count;
	}
	public int getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}
	public int getCheck_flag() {
		return check_flag;
	}
	public void setCheck_flag(int check_flag) {
		this.check_flag = check_flag;
	}
	public int getShow_flag() {
		return show_flag;
	}
	public void setShow_flag(int show_flag) {
		this.show_flag = show_flag;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Date getShow_time() {
		return show_time;
	}
	public void setShow_time(Date show_time) {
		this.show_time = show_time;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}
	public int getBook_add_count() {
		return book_add_count;
	}
	public void setBook_add_count(int book_add_count) {
		this.book_add_count = book_add_count;
	}
	public long getBook_count() {
		return book_count;
	}
	public void setBook_count(long book_count) {
		this.book_count = book_count;
	}
	public Date getFresh_time() {
		return fresh_time;
	}
	public void setFresh_time(Date fresh_time) {
		this.fresh_time = fresh_time;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + book_add_count;
		result = prime * result + (int) (book_count ^ (book_count >>> 32));
		result = prime * result + ((book_list_description == null) ? 0 : book_list_description.hashCode());
		result = prime * result + ((book_list_name == null) ? 0 : book_list_name.hashCode());
		result = prime * result + check_flag;
		result = prime * result + (int) (collect_count ^ (collect_count >>> 32));
		result = prime * result + (int) (comment_count ^ (comment_count >>> 32));
		result = prime * result + ((create_time == null) ? 0 : create_time.hashCode());
		result = prime * result + del_flag;
		result = prime * result + ((fresh_time == null) ? 0 : fresh_time.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((operate_time == null) ? 0 : operate_time.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((owner_description == null) ? 0 : owner_description.hashCode());
		result = prime * result + (int) (owner_id ^ (owner_id >>> 32));
		result = prime * result + show_flag;
		result = prime * result + ((show_time == null) ? 0 : show_time.hashCode());
		result = prime * result + type;
		result = prime * result + ((update_time == null) ? 0 : update_time.hashCode());
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
		Booklist other = (Booklist) obj;
		if (book_add_count != other.book_add_count)
			return false;
		if (book_count != other.book_count)
			return false;
		if (book_list_description == null) {
			if (other.book_list_description != null)
				return false;
		} else if (!book_list_description.equals(other.book_list_description))
			return false;
		if (book_list_name == null) {
			if (other.book_list_name != null)
				return false;
		} else if (!book_list_name.equals(other.book_list_name))
			return false;
		if (check_flag != other.check_flag)
			return false;
		if (collect_count != other.collect_count)
			return false;
		if (comment_count != other.comment_count)
			return false;
		if (create_time == null) {
			if (other.create_time != null)
				return false;
		} else if (!create_time.equals(other.create_time))
			return false;
		if (del_flag != other.del_flag)
			return false;
		if (fresh_time == null) {
			if (other.fresh_time != null)
				return false;
		} else if (!fresh_time.equals(other.fresh_time))
			return false;
		if (id != other.id)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (operate_time == null) {
			if (other.operate_time != null)
				return false;
		} else if (!operate_time.equals(other.operate_time))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (owner_description == null) {
			if (other.owner_description != null)
				return false;
		} else if (!owner_description.equals(other.owner_description))
			return false;
		if (owner_id != other.owner_id)
			return false;
		if (show_flag != other.show_flag)
			return false;
		if (show_time == null) {
			if (other.show_time != null)
				return false;
		} else if (!show_time.equals(other.show_time))
			return false;
		if (type != other.type)
			return false;
		if (update_time == null) {
			if (other.update_time != null)
				return false;
		} else if (!update_time.equals(other.update_time))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Booklist [id=" + id + ", owner_id=" + owner_id + ", book_list_name=" + book_list_name
				+ ", book_list_description=" + book_list_description + ", owner_description=" + owner_description
				+ ", label=" + label + ", type=" + type + ", collect_count=" + collect_count + ", comment_count="
				+ comment_count + ", del_flag=" + del_flag + ", check_flag=" + check_flag + ", show_flag=" + show_flag
				+ ", create_time=" + create_time + ", update_time=" + update_time + ", show_time=" + show_time
				+ ", operator=" + operator + ", operate_time=" + operate_time + ", book_add_count=" + book_add_count
				+ ", book_count=" + book_count + ", fresh_time=" + fresh_time + "]";
	}

}
