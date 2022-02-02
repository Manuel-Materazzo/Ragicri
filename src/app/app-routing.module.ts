import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {BaseLayoutComponent} from './Layout/base-layout/base-layout.component';
import {PagesLayoutComponent} from './Layout/pages-layout/pages-layout.component';

// DEMO PAGES

// Dashboards

import {AnalyticsComponent} from './Pages/Dashboards/analytics/analytics.component';

// Pages

import {ForgotPasswordBoxedComponent} from './Pages/UserPages/forgot-password-boxed/forgot-password-boxed.component';
import {LoginBoxedComponent} from './Pages/UserPages/login-boxed/login-boxed.component';
import {RegisterBoxedComponent} from './Pages/UserPages/register-boxed/register-boxed.component';

// Elements

import {StandardComponent} from './Pages/Elements/Buttons/standard/standard.component';
import {DropdownsComponent} from './Pages/Elements/dropdowns/dropdowns.component';
import {CardsComponent} from './Pages/Elements/cards/cards.component';
import {ListGroupsComponent} from './Pages/Elements/list-groups/list-groups.component';
import {TimelineComponent} from './Pages/Elements/timeline/timeline.component';
import {IconsComponent} from './Pages/Elements/icons/icons.component';

// Components

import {AccordionsComponent} from './Pages/Components/accordions/accordions.component';
import {TabsComponent} from './Pages/Components/tabs/tabs.component';
import {CarouselComponent} from './Pages/Components/carousel/carousel.component';
import {ModalsComponent} from './Pages/Components/modals/modals.component';
import {ProgressBarComponent} from './Pages/Components/progress-bar/progress-bar.component';
import {PaginationComponent} from './Pages/Components/pagination/pagination.component';
import {TooltipsPopoversComponent} from './Pages/Components/tooltips-popovers/tooltips-popovers.component';

// Tables

import {TablesMainComponent} from './Pages/Tables/tables-main/tables-main.component';

// Widgets

import {ChartBoxes3Component} from './Pages/Widgets/chart-boxes3/chart-boxes3.component';

// Forms Elements

import {ControlsComponent} from './Pages/Forms/Elements/controls/controls.component';
import {LayoutComponent} from './Pages/Forms/Elements/layout/layout.component';

// Charts

import {ChartjsComponent} from './Pages/Charts/chartjs/chartjs.component';
import {HomeComponent} from './Pages/Dashboards/home/home/home.component';
import {MenuComponent} from './Pages/Menu/menu.component';
import {OrdiniCamerieriComponent} from './Pages/OrdiniCamerieri/ordini-camerieri/ordini-camerieri.component';
import {CucinaComponent} from './Pages/Cucina/cucina/cucina.component';
import {PiattiAdminComponent} from './Pages/Admin/piatti-admin/piatti-admin.component';
import {CarrelloComponent} from './Pages/Carrello/carrello/carrello.component';
import {OrdiniAsportoComponent} from './Pages/ordini-asporto/ordini-asporto.component';
import {UtenteAdminComponent} from './Pages/Admin/utente-admin/utente-admin/utente-admin.component';
import {AreaUtenteComponent} from './Pages/areaUtente/area-utente/area-utente.component';

const routes: Routes = [
  {
    path: '',
    component: BaseLayoutComponent,
    children: [

      // Dashboards

      {path: '', component: HomeComponent, data: {extraParameter: 'dashboardsMenu'}},

      // Elements

      {path: 'menuComponent', component: MenuComponent, data: {extraParameter: ''}},
      {path: 'carrello', component: CarrelloComponent, data:{extraParameter: ''}},
      {path: 'camerieri', component: OrdiniCamerieriComponent, data: {extraParameter: ''}},
      {path: 'cucina', component: CucinaComponent, data:{extraParameter: ''}},
      {path: 'adminPiatti', component: PiattiAdminComponent, data:{extraParameter: 'admin'}},
      {path: 'adminUtenti', component: UtenteAdminComponent, data:{extraParameter: 'admin'}},
      {path: 'ordiniAsporto', component: OrdiniAsportoComponent, data:{extraParameter: 'ordiniAsporto'}},
      {path: 'areaUtente', component: AreaUtenteComponent, data:{extraParameter: ''}},

      {path: 'elements/buttons-standard', component: StandardComponent, data: {extraParameter: 'elementsMenu'}},
      {path: 'elements/dropdowns', component: DropdownsComponent, data: {extraParameter: 'elementsMenu'}},
      {path: 'elements/icons', component: IconsComponent, data: {extraParameter: 'elementsMenu'}},
      {path: 'elements/cards', component: CardsComponent, data: {extraParameter: 'elementsMenu'}},
      {path: 'elements/list-group', component: ListGroupsComponent, data: {extraParameter: 'elementsMenu'}},
      {path: 'elements/timeline', component: TimelineComponent, data: {extraParameter: 'elementsMenu'}},

      // Components

      {path: 'components/tabs', component: TabsComponent, data: {extraParameter: 'componentsMenu'}},
      {path: 'components/accordions', component: AccordionsComponent, data: {extraParameter: 'componentsMenu'}},
      {path: 'components/modals', component: ModalsComponent, data: {extraParameter: 'componentsMenu'}},
      {path: 'components/progress-bar', component: ProgressBarComponent, data: {extraParameter: 'componentsMenu'}},
      {path: 'components/tooltips-popovers', component: TooltipsPopoversComponent, data: {extraParameter: 'componentsMenu'}},
      {path: 'components/carousel', component: CarouselComponent, data: {extraParameter: 'componentsMenu'}},
      {path: 'components/pagination', component: PaginationComponent, data: {extraParameter: 'componentsMenu'}},

      // Tables

      {path: 'tables/bootstrap', component: TablesMainComponent, data: {extraParameter: 'tablesMenu'}},

      // Widgets

      {path: 'widgets/chart-boxes-3', component: ChartBoxes3Component, data: {extraParameter: 'pagesMenu3'}},

      // Forms Elements

      {path: 'forms/controls', component: ControlsComponent, data: {extraParameter: 'formElementsMenu'}},
      {path: 'forms/layouts', component: LayoutComponent, data: {extraParameter: 'formElementsMenu'}},

      // Charts

      {path: 'charts/chartjs', component: ChartjsComponent, data: {extraParameter: ''}},

    ]

  },
  {
    path: '',
    component: PagesLayoutComponent,
    children: [

      // User Pages

      {path: 'login', component: LoginBoxedComponent, data: {extraParameter: ''}},
      {path: 'registra', component: RegisterBoxedComponent, data: {extraParameter: ''}},
      {path: 'passwordDimenticata', component: ForgotPasswordBoxedComponent, data: {extraParameter: ''}},
    ]
  },
    //se non trova la pagina
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,
    {
      scrollPositionRestoration: 'enabled',
      anchorScrolling: 'enabled',
      enableTracing: true
    })],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
