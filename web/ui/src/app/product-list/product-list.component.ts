import { Component, OnInit } from '@angular/core';
import { Product } from '../common/model/product.model';
import {ProductService} from '../common/service/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})

export class ProductListComponent implements OnInit {

  products:Product[];

  constructor(private productService:ProductService){ }

  ngOnInit(): void {
  	this.getProducts();
  }

  getProducts():void{
  	this.productService.getProducts()
  		.subscribe(
  			products=>{
  			JSON.stringify(products)
  			console.log("products");
  			this.products=products;
  		});
  }
}
