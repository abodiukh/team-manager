package com.levi9.components;

import java.util.Optional;
import java.util.Set;

import com.levi9.domain.Employee;
import com.levi9.domain.Rate;
import com.levi9.domain.Team;
import com.levi9.domain.TeamMember;

import org.springframework.stereotype.Component;

@Component
public final class TeamCalculator {

    public Integer getCost(final Team team) {
        Set<Rate> rates = team.getRates();
        return team.getTeamMembers().stream()
                .filter(TeamCalculator::validTeamMember)
                .map(tm -> getTeamMemberCost(rates, tm))
                .reduce(0, (x, y) -> x + y);
    }

    private static boolean validTeamMember(final TeamMember teamMember) {
        Employee employee = teamMember.getEmployee();
        return employee != null && employee.getSeniority() != null && employee.getPosition() != null;
    }

    private static int getTeamMemberCost(final Set<Rate> rates, final TeamMember teamMember) {
        Employee employee = teamMember.getEmployee();
        Optional<Rate> rate = rates.stream().filter(r -> r.getSeniority().getId().equals(employee.getSeniority().getId())
                && r.getPosition().getId().equals(employee.getPosition().getId())).findFirst();
        if (rate.isPresent()) {
            int involvement = teamMember.getInvolvement() != null ? teamMember.getInvolvement() : 100;
            return rate.get().getRate() * involvement / 100;
        }
        return 0;
    }
}
