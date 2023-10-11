package hu.progmasters.kingfishair.dto.adminRouteList;

public class AdminRouteListOrderFilter {

    private Long order;

    private String filterDepAirportName;

    private String filterArrAirportName;

    private Long itemPerPage;

    private Long page;

    public AdminRouteListOrderFilter() {
    }

    public AdminRouteListOrderFilter(Long order, String filterDepAirportName, String filterArrAirportName, Long itemPerPage, Long page) {
        this.order = order;
        this.filterDepAirportName = filterDepAirportName;
        this.filterArrAirportName = filterArrAirportName;
        this.itemPerPage = itemPerPage;
        this.page = page;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public String getFilterDepAirportName() {
        return filterDepAirportName;
    }

    public void setFilterDepAirportName(String filterDepAirportName) {
        this.filterDepAirportName = filterDepAirportName;
    }

    public String getFilterArrAirportName() {
        return filterArrAirportName;
    }

    public void setFilterArrAirportName(String filterArrAirportName) {
        this.filterArrAirportName = filterArrAirportName;
    }

    public Long getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(Long itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

}
