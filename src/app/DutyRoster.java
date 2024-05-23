package app;

import adt.*;

import java.util.Set;
import java.util.HashSet;

public class DutyRoster {
  private IIntervalSet<Employee> dutyRoster;
  private Set<Employee> employees;
  private long begin;
  private long end;
  
  DutyRoster(long start, long end) {
    dutyRoster = new Single<>(new Full<>(new Disjoint<>(
        new IntervalSet<Employee>()), start, end));
    employees = new HashSet<>();
    begin = start;
    this.end = end;
  }
  
  public void addEmployee(Employee employee) {
    employees.add(employee);
  }
  
  public void removeEmployee(Employee employee) {
    employees.remove(employee);
  }
  
  public void assignShift(Employee employee, long start, long end) {
    dutyRoster.insert(new Interval<Employee>(employee, start, end));
  }
  
  public void unassignShift(Employee employee, long start, long end) {
    dutyRoster.remove(new Interval<Employee>(employee, start, end));
  }
  
//  public void autoAssignShifts() {
//    for (Employee employee : employees) {
//      if (dutyRoster.contains(employee)) {
//        continue;
//      }
//
//      dutyRoster.insert(new Interval<Employee>(employee, begin, end));
//    }
//  }
}
