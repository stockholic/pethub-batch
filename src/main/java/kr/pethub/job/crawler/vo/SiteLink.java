package kr.pethub.job.crawler.vo;

public class SiteLink {
	
	private String linkSrl;				// 링크 일련번호
	private String siteSrl;					// 사이트 일련번호
	private String siteNm;				// 사이트명 
	private String linkNm;				// 링크명
	private String linkUrl;				// 링크 URL
	private String linkCls;				// 실행 클래스
	private String linkMtdLst;			// 실행 목록 메소드
	private String linkMtdCts;			// 실행 내용 메소드
	private int linkCnt;					// 링크수 
	
	public String getLinkSrl() {
		return linkSrl;
	}
	public void setLinkSrl(String linkSrl) {
		this.linkSrl = linkSrl;
	}
	public String getSiteSrl() {
		return siteSrl;
	}
	public void setSiteSrl(String siteSrl) {
		this.siteSrl = siteSrl;
	}
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	public String getLinkNm() {
		return linkNm;
	}
	public void setLinkNm(String linkNm) {
		this.linkNm = linkNm;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getLinkCls() {
		return linkCls;
	}
	public void setLinkCls(String linkCls) {
		this.linkCls = linkCls;
	}
	public String getLinkMtdLst() {
		return linkMtdLst;
	}
	public void setLinkMtdLst(String linkMtdLst) {
		this.linkMtdLst = linkMtdLst;
	}
	public String getLinkMtdCts() {
		return linkMtdCts;
	}
	public void setLinkMtdCts(String linkMtdCts) {
		this.linkMtdCts = linkMtdCts;
	}
	public int getLinkCnt() {
		return linkCnt;
	}
	public void setLinkCnt(int linkCnt) {
		this.linkCnt = linkCnt;
	}
	
	
}
