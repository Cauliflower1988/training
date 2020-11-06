import { Injectable } from '@angular/core';
import { Product } from '../model/product.model';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class ProductService {
  private serverApiUrl = '/training';  // URL to web api
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

   constructor(private http: HttpClient) { }

   getProducts():Observable<Product[]>{
   	const url = `${this.serverApiUrl}/products`;


    this.http.get(url, this.httpOptions)
     .pipe(
        tap((data:any) => console.log('data: ', data)),
        catchError((val: Error) => of(`I caught: ${val}`))
      );
     return this.http.get<Product[]>(url, this.httpOptions);

  }

  getProductById(id:string):Observable<Product>{
    const url = `${this.serverApiUrl}/product/${id}`;
  	return this.http.get<Product>(url, this.httpOptions);
  }

  getProductByCode(code:string):Observable<Product>{
    const url = `${this.serverApiUrl}/product/${code}`;
    console.log('url: '+url);
  	return this.http.get<Product>(url,this.httpOptions);
  }
}
