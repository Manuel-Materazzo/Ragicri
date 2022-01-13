import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgReduxModule} from '@angular-redux/store';
import {NgRedux, DevToolsExtension} from '@angular-redux/store';
import {rootReducer, ArchitectUIState} from './Theme/store';
import {ConfigActions} from './Theme/store/config.actions';
import {AppRoutingModule} from './app-routing.module';
import {LoadingBarRouterModule} from '@ngx-loading-bar/router';

import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';

// BOOTSTRAP COMPONENTS

import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {PerfectScrollbarModule} from 'ngx-perfect-scrollbar';
import {PERFECT_SCROLLBAR_CONFIG} from 'ngx-perfect-scrollbar';
import {PerfectScrollbarConfigInterface} from 'ngx-perfect-scrollbar';
import {ChartsModule} from 'ng2-charts';

// LAYOUT

import {BaseLayoutComponent} from './Layout/base-layout/base-layout.component';
import {PagesLayoutComponent} from './Layout/pages-layout/pages-layout.component';
import {PageTitleComponent} from './Layout/Components/page-title/page-title.component';

// HEADER

import {HeaderComponent} from './Layout/Components/header/header.component';
import {SearchBoxComponent} from './Layout/Components/header/elements/search-box/search-box.component';
import {UserBoxComponent} from './Layout/Components/header/elements/user-box/user-box.component';

// SIDEBAR

import {SidebarComponent} from './Layout/Components/sidebar/sidebar.component';


// FOOTER

import {FooterComponent} from './Layout/Components/footer/footer.component';

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

import {RegularComponent} from './Pages/Tables/regular/regular.component';
import {TablesMainComponent} from './Pages/Tables/tables-main/tables-main.component';

// Widgets

import {ChartBoxes3Component} from './Pages/Widgets/chart-boxes3/chart-boxes3.component';

// Forms Elements

import {ControlsComponent} from './Pages/Forms/Elements/controls/controls.component';
import {LayoutComponent} from './Pages/Forms/Elements/layout/layout.component';

// Charts

import {ChartjsComponent} from './Pages/Charts/chartjs/chartjs.component';

// Chart.js Examples

import {LineChartComponent} from './Pages/Charts/chartjs/examples/line-chart/line-chart.component';
import {BarChartComponent} from './Pages/Charts/chartjs/examples/bar-chart/bar-chart.component';
import {ScatterChartComponent} from './Pages/Charts/chartjs/examples/scatter-chart/scatter-chart.component';
import {RadarChartComponent} from './Pages/Charts/chartjs/examples/radar-chart/radar-chart.component';
import {PolarAreaChartComponent} from './Pages/Charts/chartjs/examples/polar-area-chart/polar-area-chart.component';
import {BubbleChartComponent} from './Pages/Charts/chartjs/examples/bubble-chart/bubble-chart.component';
import {DynamicChartComponent} from './Pages/Charts/chartjs/examples/dynamic-chart/dynamic-chart.component';
import {DoughnutChartComponent} from './Pages/Charts/chartjs/examples/doughnut-chart/doughnut-chart.component';
import {PieChartComponent} from './Pages/Charts/chartjs/examples/pie-chart/pie-chart.component';
import { SideBarCarrelloComponent } from './Layout/Components/side-bar-carrello/side-bar-carrello.component';
import { HomeComponent } from './Pages/Dashboards/home/home/home.component';
import { MenuComponent } from './Pages/Menu/menu.component';
import { OrdiniCamerieriComponent } from './Pages/OrdiniCamerieri/ordini-camerieri/ordini-camerieri.component';
import { CucinaComponent } from './Pages/Cucina/cucina/cucina.component';
import { PiattiAdminComponent } from './Pages/Admin/piatti-admin/piatti-admin.component';

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

@NgModule({
  declarations: [

    // LAYOUT

    AppComponent,
    BaseLayoutComponent,
    PagesLayoutComponent,
    PageTitleComponent,

    // HEADER

    HeaderComponent,
    SearchBoxComponent,
    UserBoxComponent,

    // SIDEBAR

    SidebarComponent,

    // FOOTER

    FooterComponent,

    // DEMO PAGES

    // Dashboards

    AnalyticsComponent,

    // User Pages

    ForgotPasswordBoxedComponent,
    LoginBoxedComponent,
    RegisterBoxedComponent,

    // Elements

    StandardComponent,
    IconsComponent,
    DropdownsComponent,
    CardsComponent,
    ListGroupsComponent,
    TimelineComponent,

    // Components

    AccordionsComponent,
    TabsComponent,
    CarouselComponent,
    ModalsComponent,
    ProgressBarComponent,
    PaginationComponent,
    TooltipsPopoversComponent,

    // Tables

    RegularComponent,
    TablesMainComponent,

    // Dashboard Boxes

    ChartBoxes3Component,

    // Form Elements

    ControlsComponent,
    LayoutComponent,

    // CHARTS

    ChartjsComponent,

    // Chart.js Examples

    LineChartComponent,
    BarChartComponent,
    DoughnutChartComponent,
    RadarChartComponent,
    PieChartComponent,
    PolarAreaChartComponent,
    DynamicChartComponent,
    BubbleChartComponent,
    ScatterChartComponent,
    SideBarCarrelloComponent,
    HomeComponent,
    MenuComponent,
    OrdiniCamerieriComponent,
    CucinaComponent,
    PiattiAdminComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgReduxModule,
    CommonModule,
    LoadingBarRouterModule,

    // Angular Bootstrap Components

    PerfectScrollbarModule,
    NgbModule,
    AngularFontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,

    // Charts

    ChartsModule,
  ],
  providers: [
    {
      provide:
      PERFECT_SCROLLBAR_CONFIG,
      // DROPZONE_CONFIG,
      useValue:
      DEFAULT_PERFECT_SCROLLBAR_CONFIG,
      // DEFAULT_DROPZONE_CONFIG,
    },
    ConfigActions,
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
  constructor(private ngRedux: NgRedux<ArchitectUIState>,
              private devTool: DevToolsExtension) {

    this.ngRedux.configureStore(
      rootReducer,
      {} as ArchitectUIState,
      [],
      [devTool.isEnabled() ? devTool.enhancer() : f => f]
    );

  }
}
