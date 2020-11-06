import { Component, OnInit } from '@angular/core';
import { Product } from '../common/model/product.model';
import {ProductService} from '../common/service/product.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product:Product;

  constructor(private productService:ProductService,   
  private route: ActivatedRoute,
  private location: Location) { }


  ngOnInit(): void {
  	this.getProductByCode();
  }
  getProductByCode():void{
  	const code = +this.route.snapshot.paramMap.get('code');
  	console.log('getProductByCode param code: '+code);
  	this.productService.getProductByCode(code.toString()).subscribe(product=>this.product=product);
  	console.log(this.product);
  }

  getProductById():void{
    const id = +this.route.snapshot.paramMap.get('id');
    this.productService.getProductById(id.toString()).subscribe(product=>this.product=product);
  }

}
