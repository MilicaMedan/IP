package beans;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.StatisticDAO;
import dto.Statistic;

@ManagedBean(name = "statisticBean")
@SessionScoped
public class StatisticBean implements Serializable{
	
	private static final long serialVersionUID = -8606687324815905107L;
	private List<Integer> data;
	private List<String> labels;
	private List<Statistic> statistics;
	

	
	public List<Integer> getData() {
		selectBetween();
		data = new ArrayList<Integer>();
		for(int i=0;i<statistics.size();i++) {
			data.add(statistics.get(i).getNumberOfUsers());
		}
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	public List<String> getLabels() {
		selectBetween();
		labels = new ArrayList<String>();
		for(int i=0;i<statistics.size();i++) {
			String label=""+statistics.get(i).getHour();
			System.out.println(label);
			labels.add(label);
		}
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public StatisticBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Statistic> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<Statistic> statistics) {
		this.statistics = statistics;
	}

	
	
	private void selectBetween() {
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		int hour = now.getHour();
		int lastDay= 0;
		int lastMonth=0;
		int lastYear=0;
		List<Statistic> statistics1 = new ArrayList<Statistic>();
		List<Statistic> statistics2= new ArrayList<Statistic>();
		
		if(day ==1 && month == 1) {
			lastYear= year-1;
			lastMonth=12;
			lastDay=31;
			statistics1= StatisticDAO.selectData(year, month, day, 0, hour);
			statistics2 =StatisticDAO.selectData(lastYear, lastMonth, lastDay, hour-1, 24);
		}else if(day==1 && month !=1) {
			lastMonth=month-1;
			if(lastMonth == 1 || lastMonth == 3 || lastMonth == 5 || lastMonth == 7 || lastMonth == 8 || lastMonth == 10 || lastMonth == 12 )
			{
				lastDay=31;
			}else if(lastMonth==2) {
				if((((year % 4 == 0) && (year % 100 != 0)) ||  (year % 400 == 0))) {
					lastDay=29;
				}else {
					lastDay=28;
				}
			}else {
				lastDay=30;
			}
			
			statistics1= StatisticDAO.selectData(year, month, day, 0,hour);
			statistics2 =StatisticDAO.selectData(year, lastMonth, lastDay, hour-1,24);
		}else {
			lastDay=day-1;
			statistics1= StatisticDAO.selectData(year, month, day, 0,hour);
			statistics2 =StatisticDAO.selectData(year, month, lastDay, hour-1,24);
		}
		
		
		
		statistics=statistics2;
		statistics.addAll(statistics1);
	}

}
