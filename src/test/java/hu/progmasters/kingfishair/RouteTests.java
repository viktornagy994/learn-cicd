package hu.progmasters.kingfishair;

import hu.progmasters.kingfishair.dto.adminRouteList.AdminRouteListOrderFilter;
import hu.progmasters.kingfishair.dto.adminRouteList.RouteListItem;
import hu.progmasters.kingfishair.service.RouteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RouteTests {

    private RouteService routeService;

    @Autowired
    public RouteTests(RouteService routeService) {
        this.routeService = routeService;
    }


    @Test
    public void test_getFilteredRouteListWithArrAirportName() {

        AdminRouteListOrderFilter adminRouteListOrderFilter = new AdminRouteListOrderFilter(
                1L, "", "hels", 2L, 0L
        );

        List<RouteListItem> routeListItemList = routeService.getRouteForAdmin(adminRouteListOrderFilter);
        boolean testResult = (routeListItemList.size() == 2)
                && (routeListItemList.get(0).getId() == 9)
                && (routeListItemList.get(0).getDepIata().equals("ARN"))
                && (routeListItemList.get(0).getArrIata().equals("HEL"))
                && (routeListItemList.get(1).getId() == 4)
                && (routeListItemList.get(1).getDepIata().equals("KEF"))
                && (routeListItemList.get(1).getArrIata().equals("HEL"))
                ;
        assertTrue(testResult);

    }

    @Test
    public void test_getFilteredRouteListWithDepAirportName() {

        AdminRouteListOrderFilter adminRouteListOrderFilter = new AdminRouteListOrderFilter(
                2L, "arn", "", 3L, 0L
        );

        List<RouteListItem> routeListItemList = routeService.getRouteForAdmin(adminRouteListOrderFilter);
        boolean testResult = (routeListItemList.size() == 3)
                && (routeListItemList.get(0).getId() == 9)
                && (routeListItemList.get(0).getDepIata().equals("ARN"))
                && (routeListItemList.get(0).getArrCity().equals("Helsinki"))
                && (routeListItemList.get(2).getId() == 2)
                && (routeListItemList.get(2).getDepIata().equals("ARN"))
                && (routeListItemList.get(2).getArrCity().equals("Oslo"))
                ;
        assertTrue(testResult);

    }

    @ParameterizedTest
    @CsvSource({
            "1,,,100,0,11",
            "1,,hels,2,0,2",
            "2,arn,,3,0,3",
            "1,,,4,0,4",
            "2,stockholm,Reykjavik,500,0,1",
    })
    public void test_csv_getFilteredRouteListWithDepAirportName(Long order, String depFilter, String arrFilter, Long itemPerPage, Long page, int listSize) {

        if (depFilter == null) { depFilter = "";}
        if (arrFilter == null) { arrFilter = "";}

        AdminRouteListOrderFilter adminRouteListOrderFilter = new AdminRouteListOrderFilter(
           order, depFilter, arrFilter, itemPerPage, page
        );

        List<RouteListItem> routeListItemList = routeService.getRouteForAdmin(adminRouteListOrderFilter);
        boolean testResult = (routeListItemList.size() == listSize);
        assertTrue(testResult);

    }


}
