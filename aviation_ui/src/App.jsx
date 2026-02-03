import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout/Layout';
import HomePage from './pages/HomePage';
import LocationsPage from './pages/LocationsPage';
import TransportationsPage from './pages/TransportationsPage';
import RouteSearchPage from './pages/RouteSearchPage';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<HomePage />} />
          <Route path="locations" element={<LocationsPage />} />
          <Route path="transportations" element={<TransportationsPage />} />
          <Route path="routes" element={<RouteSearchPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
