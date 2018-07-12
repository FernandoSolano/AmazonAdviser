package domain;

public class Query {
    private String keywords;
    private int queryLimit;

    public Query() {
    }

    public Query(String keywords, int queryLimit) {
        this.keywords = keywords;
        this.queryLimit = queryLimit;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getQueryLimit() {
        return queryLimit;
    }

    public void setQueryLimit(int queryLimit) {
        this.queryLimit = queryLimit;
    }
}
