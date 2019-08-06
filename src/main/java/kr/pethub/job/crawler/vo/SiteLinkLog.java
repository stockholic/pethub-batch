package kr.pethub.job.crawler.vo;

public class SiteLinkLog {
	
	private String logSrl;			// 로그 일련번호 
	private String siteSrl;			// 사이트 일련번호
	private String linkSrl;		// 	링크 일련번호
	private int linkCnt;			//	링크 수 
	private String logCd;			// 로그 코드 	
	private String logMsg;		// 	로그 메세지
	
	public String getLogSrl() {
		return logSrl;
	}
	public void setLogSrl(String logSrl) {
		this.logSrl = logSrl;
	}
	public String getSiteSrl() {
		return siteSrl;
	}
	public void setSiteSrl(String siteSrl) {
		this.siteSrl = siteSrl;
	}
	public String getLinkSrl() {
		return linkSrl;
	}
	public void setLinkSrl(String linkSrl) {
		this.linkSrl = linkSrl;
	}
	public int getLinkCnt() {
		return linkCnt;
	}
	public void setLinkCnt(int linkCnt) {
		this.linkCnt = linkCnt;
	}
	public String getLogCd() {
		return logCd;
	}
	public void setLogCd(String logCd) {
		this.logCd = logCd;
	}
	public String getLogMsg() {
		return logMsg;
	}
	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

}
