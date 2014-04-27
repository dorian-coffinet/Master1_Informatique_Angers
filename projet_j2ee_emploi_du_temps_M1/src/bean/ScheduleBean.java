package bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TreeMap;

import actions.abstractAction.AbstractAction;
import model.org.persistence.ScheduleEntity;

public class ScheduleBean
{
	private long id;
	private long idTeacher;
	private long idSubject;
	private long idClassroom;
	private long idGroup;
	private String dayStart;
	private String dayEnd;
	private String name;
	private String comment;
	private String colorSubject;
	private TreeMap<Long, String> mapGroup;
	private TreeMap<Long, String> mapClassroom;
	private TreeMap<Long, String> mapTeacher;
	private TreeMap<Long, String> mapSubject;

	/**
	 * Contructeur
	 */
	public ScheduleBean()
	{
		super();
		this.id = 0;
		this.idTeacher = 0;
		this.idSubject = 0;
		this.idClassroom = 0;
		this.idGroup = 0;
		this.dayStart = "";
		this.dayEnd = "";
		this.name = "";
		this.comment = "";
		this.colorSubject = "";
	}

	
	/**
	 * Méthode de conversion avec un ScheduleEntity en entrée et un userBean en sortie
	 * @param ScheduleEntity
	 */
	public void convertEntityToBean(ScheduleEntity se) throws ParseException
	{
		setId(se.getId());
		setName(se.getName());
		setDayStart(AbstractAction.SIMPLE_DATE_FORMAT.format(AbstractAction.SIMPLE_DATE_FORMAT.parse(se.getDayStart())));
		setDayEnd(AbstractAction.SIMPLE_DATE_FORMAT.format(AbstractAction.SIMPLE_DATE_FORMAT.parse(se.getDayEnd())));
		setComment(se.getComment());
		setIdTeacher(se.getUserTeacher().getId());
		setIdSubject(se.getSubject().getId());
		setIdClassroom(se.getClassroom().getId());
		setIdGroup(se.getGroup().getId());
		setColorSubject(se.getSubject().getColor());
	}

	
	/**
	 * Getters and setters
	 * 
	 */
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
		if (dayStart == null)
		{
			dayStart = "";
		}
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
		if (dayEnd == null)
		{
			dayEnd = "";
		}
		return simpleDateFormat.format(simpleDateFormat.parse(dayEnd));
	}

	public void setDayEnd(String dayEnd)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			this.dayEnd = simpleDateFormat.format(simpleDateFormat.parse(dayEnd));
		} catch (ParseException e)
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

	public String getColorSubject()
	{
		return colorSubject;
	}

	public void setColorSubject(String colorSubject)
	{
		this.colorSubject = colorSubject;
	}

	public long getIdClassroom() 
	{
		return idClassroom;
	}

	public void setIdClassroom(long idClassroom) 
	{
		this.idClassroom = idClassroom;
	}

	public TreeMap<Long, String> getMapClassroom() 
	{
		return mapClassroom;
	}

	public void setMapClassroom(TreeMap<Long, String> mapClassroom) 
	{
		this.mapClassroom = mapClassroom;
	}

	public TreeMap<Long, String> getMapGroup() 
	{
		return mapGroup;
	}

	public void setMapGroup(TreeMap<Long, String> mapGroup) 
	{
		this.mapGroup = mapGroup;
	}

	public TreeMap<Long, String> getMapTeacher() 
	{
		return mapTeacher;
	}

	public void setMapTeacher(TreeMap<Long, String> mapTeacher)
	{
		this.mapTeacher = mapTeacher;
	}


	public TreeMap<Long, String> getMapSubject() 
	{
		return mapSubject;
	}

	public void setMapSubject(TreeMap<Long, String> mapSubject) 
	{
		this.mapSubject = mapSubject;
	}

	public long getIdSubject() 
	{
		return idSubject;
	}

	public void setIdSubject(long idSubject)
	{
		this.idSubject = idSubject;
	}

	public long getIdGroup() 
	{
		return idGroup;
	}

	public void setIdGroup(long idGroup) 
	{
		this.idGroup = idGroup;
	}

	public long getIdTeacher() 
	{
		return idTeacher;
	}

	public void setIdTeacher(long idTeacher) 
	{
		this.idTeacher = idTeacher;
	}
}
