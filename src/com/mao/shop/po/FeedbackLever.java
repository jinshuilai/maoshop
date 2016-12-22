package com.mao.shop.po;

public class FeedbackLever {

	private int allFeed;
	private int praise;
	private int medEval;
	private int bad;
	private int praiseRate;
	public int getAllFeed() {
		return allFeed;
	}
	public void setAllFeed(int allFeed) {
		this.allFeed = allFeed;
	}
	public int getPraise() {
		return praise;
	}
	public void setPraise(int praise) {
		this.praise = praise;
	}
	public int getMedEval() {
		return medEval;
	}
	public void setMedEval(int medEval) {
		this.medEval = medEval;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public int getPraiseRate() {
		praiseRate =  (int) (praise * 1.0 / allFeed * 100);
		return praiseRate;
	}

}
