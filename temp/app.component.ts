import { Component, OnInit, ViewChild, AfterViewInit, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';

class Person {
  fullName: string;
  username: string;
  shortName: string;
  status:string;
  role:string;
}

class DataTablesResponse {
  data: any[];
  draw: number;
  recordsFiltered: number;
  recordsTotal: number;
}


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit, OnDestroy, OnInit {

  @ViewChild(DataTableDirective, {static: false})
  dtElement: DataTableDirective;
  dtTrigger: Subject<any> = new Subject();
  dtOptions: DataTables.Settings = {};
  persons: Person[];
  role="PS";
  status="REGISTERED"
  filters = {};
  constructor(private http:HttpClient ){
  }

  filterDataStatus(value){
    this.status = value;
  }
  filterDataRole(value){
    this.role = value;
  }
  searchData(){
    this.rerender();
    this.filters ={"role":this.role,"status":this.status}
    console.log(this.filters);
   
    this.drawDataTable();
  }
  ngOnInit():void{
      this.filters =[ {"role":this.role,"status":this.status}]
      this.drawDataTable() 
  }

  drawDataTable(){
    const that = this;
     this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      serverSide: true,
      processing: true,
      destroy:true,
      ajax: (dataTablesParameters: any, callback) => {
        that.http
          .post<DataTablesResponse>(
            'http://localhost:8080/api/user/reports/users',
            Object.assign(dataTablesParameters,that.filters), {
              
            }
          ).subscribe(resp => {
            that.persons = resp.data;

            callback({
              recordsTotal: resp.recordsTotal,
              recordsFiltered: resp.recordsFiltered,
              data: []
            });
          });
      },
      columns: [{ name: 'fullName' }, { name: 'username' }, { name: 'shortName' },{name:"userStatus"},{name:"role"}]
    
  }
  }

  ngAfterViewInit(): void {
    this.dtTrigger.next();
  }

  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
    this.dtTrigger.unsubscribe();
  }


  rerender(): void {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      // Destroy the table first
      dtInstance.destroy();
      // Call the dtTrigger to rerender again
      this.dtTrigger.next();
    });
  }
}
