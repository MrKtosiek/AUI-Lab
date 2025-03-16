import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { GenreListComponent } from '../views/genre-list/genre-list.component';
import { GenreAddComponent } from '../views/genre-add/genre-add.component';
import { GenreEditComponent } from '../views/genre-edit/genre-edit.component';
import { GenreDetailsComponent } from '../views/genre-details/genre-details.component';
import { BookAddComponent } from '../views/book-add/book-add.component';
import { BookEditComponent } from '../views/book-edit/book-edit.component';
import { BookDetailsComponent } from '../views/book-details/book-details.component';

export const routes: Routes = [
    { path: 'genres', component: GenreListComponent },
    { path: 'genres/add', component: GenreAddComponent },
    { path: 'genres/edit/:id', component: GenreEditComponent },
    { path: 'genres/:id/details', component: GenreDetailsComponent },
    { path: 'genres/:genreId/books/add', component: BookAddComponent },
    { path: 'genres/:genreId/books/edit/:bookId', component: BookEditComponent },
    { path: 'genres/:genreId/books/:bookId/details', component: BookDetailsComponent },
    { path: '**', redirectTo: '/genres', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
