package model.org.persistence;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
@NamedQueries
({
	@NamedQuery(name = "ScheduleEntity.findAll", query = "SELECT u FROM ScheduleEntity u"), 
	@NamedQuery(name = "ScheduleEntity.findById", query = "SELECT u FROM ScheduleEntity u WHERE u.id = :id"),
	@NamedQuery(name = "ScheduleEntity.findByGroup", query = "SELECT u FROM ScheduleEntity u WHERE u.group = :group"),
	@NamedQuery(name = "ScheduleEntity.findByClassroomAndDay", query = "SELECT u FROM ScheduleEntity u WHERE u.classroom = :classroom AND u.dayStart>=:dayStart AND u.dayStart<=:dayEnd ORDER BY u.dayStart, u.dayEnd DESC"),
	@NamedQuery(name = "ScheduleEntity.findByGroupAndDay", query = "SELECT u FROM ScheduleEntity u WHERE u.group = :group AND u.dayStart>=:dayStart AND u.dayStart<=:dayEnd ORDER BY u.dayStart, u.dayEnd DESC"),
	@NamedQuery(name = "ScheduleEntity.findByGroupAndDayWithEtu", query = "SELECT u FROM ScheduleEntity u WHERE (u.group = :group OR u.group = :groupEtu) AND u.dayStart>=:dayStart AND u.dayStart<=:dayEnd ORDER BY u.dayStart, u.dayEnd DESC"),
	@NamedQuery(name = "ScheduleEntity.findIfClassroomExist", query = "SELECT u FROM ScheduleEntity u WHERE u.classroom = :classroom AND ((u.dayStart>=:dayStart AND u.dayStart<:dayEnd) OR (u.dayEnd>=:dayStart AND u.dayEnd<=:dayEnd)) AND u.id <> :id"),
	@NamedQuery(name = "ScheduleEntity.findIfUserTeacherExist", query = "SELECT u FROM ScheduleEntity u WHERE u.userTeacher = :userTeacher AND ((u.dayStart>=:dayStart AND u.dayStart<:dayEnd) OR (u.dayEnd>:dayStart AND u.dayEnd<=:dayEnd)) AND u.id <> :id"),
	@NamedQuery(name = "ScheduleEntity.findIfGroupExist", query = "SELECT u FROM ScheduleEntity u WHERE u.group = :group AND ((u.dayStart>=:dayStart AND u.dayStart<:dayEnd) OR (u.dayEnd>:dayStart AND u.dayEnd<=:dayEnd)) AND u.id <> :id")

})
public class ScheduleEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	public ScheduleEntity()
	{

	}

	// Définition des colonnes de la table

	@Id
	@Column(name = "id_schedule")
	private long id;
	@Column(name = "day_start")
	private String dayStart;
	@Column(name = "day_end")
	private String dayEnd;
	@Column(name = "name")
	private String name;
	@Column(name = "comment")
	private String comment;
	@ManyToOne
	@JoinColumn(name = "id_user_teacher")
	private UserEntity userTeacher;
	@ManyToOne
	@JoinColumn(name = "id_subject")
	private SubjectEntity subject;
	@ManyToOne
	@JoinColumn(name = "id_classroom")
	private ClassroomEntity classroom;
	@ManyToOne
	@JoinColumn(name = "id_group_user")
	private GroupEntity group;

	// Getters et Setters

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getDayStart() throws ParseException
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(simpleDateFormat.parse(dayStart));
	}

	public void setDayStart(String dayStart)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			this.dayStart = simpleDateFormat.format(simpleDateFormat.parse(dayStart));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
	}

	public String getDayEnd() throws ParseException
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(simpleDateFormat.parse(dayEnd));
	}

	public void setDayEnd(String dayEnd) 
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			this.dayEnd = simpleDateFormat.format(simpleDateFormat.parse(dayEnd));
		} 
		catch (ParseException e)
		{
			e.printStackTrace();
		}
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public UserEntity getUserTeacher()
	{
		return userTeacher;
	}

	public void setUserTeacher(UserEntity userTeacher)
	{
		this.userTeacher = userTeacher;
	}

	public SubjectEntity getSubject()
	{
		return subject;
	}

	public void setSubject(SubjectEntity subject)
	{
		this.subject = subject;
	}

	public ClassroomEntity getClassroom()
	{
		return classroom;
	}

	public void setClassroom(ClassroomEntity classroom)
	{
		this.classroom = classroom;
	}

	public GroupEntity getGroup()
	{
		return group;
	}

	public void setGroup(GroupEntity group)
	{
		this.group = group;
	}
}
